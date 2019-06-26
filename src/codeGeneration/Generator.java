package codeGeneration;

import SyntaxTree.*;
import SyntaxTree.OperationNode;
import SyntaxTree.SubProgramNode;
import SyntaxTree.VariableNode;
import parser.*;
import SimpleScanner.*;

/**
 * This is the code generator portion of the Pascal to MIPS assembly compiler
 * @author LinaVo
 */
public class Generator {
    // global variable to keep track of the current register
    private int currentRegister;
    // global variable for the actual .asm code
    private StringBuilder asmCode;
    // the parser
    private Parser parser;
    // program node
    private ProgramNode program;
    // need to keep track of jumps
    private int currentLabel = 0;
    
    /**
     * constructor for the code generator
     * @param file 
     */
    public Generator(String file) {
        currentRegister = 0;
        asmCode = new StringBuilder();
        parser = new Parser(file);
        // parse the code, get the program node
        program = parser.program();
    }
    
    /**
     * This calls the .data section and .text sections of the asm code.
     * @return String
     */
    public String generate() {
        dataCode(program.getVariables());
        textCode(program.getMain());
        return (asmCode.toString());
    }
    
    /**
     * This section produces the .data section
     * @param declarations
     * @return String
     */
    public String dataCode(DeclarationsNode declarations) {
        // code output
        asmCode = new StringBuilder();
        // .data
        asmCode.append(".data\n");
        // iterate through var to get all the variables
        for(VariableNode varNode: declarations.getVars()) {
            // append variable node at current position
            asmCode.append(varNode.getName() + ": .word 0\n");
        }
        return (asmCode.toString());
    }

    /**
     * creates text section of the assembly code.
     * @param statements
     * @return 
     */
    public void textCode(CompoundStatementNode statements) {
        asmCode.append("\n.text\nmain:\n");
        // iterate through the list of assignment statement nodes
        compoundStatement(statements);
        // jump register, gotta return!
        asmCode.append("jr $ra\n\n");
    }
    /**
     * produces assembly code for assignment statements
     * @param assignment 
     */
    public void assignment(AssignmentStatementNode assignment) {
        // Need to figure out what kind of expression you're dealing with
        // this is if you see an value node
        if(assignment.getExpression() instanceof ValueNode) {
            valueNode((ValueNode)assignment.getExpression());
        }
        // this is if you see an operation node
        else if(assignment.getExpression() instanceof OperationNode) {
            operation((OperationNode)assignment.getExpression());
        }
        asmCode.append("sw $t").append(currentRegister).append(", ");
        asmCode.append(assignment.getLvalue().getName()).append("\n");
    }
    /**
     * produces assembly for if statements
     * @param statement 
     */
    public void ifStatement(IfStatementNode statement) {
        // check to see what kind of instance is present
        // check the instance type of the first expression in the statement
        if(statement.getIfExpression() instanceof VariableNode) {
            variableNode((VariableNode)statement.getIfExpression());
        }
        else if(statement.getIfExpression() instanceof ValueNode) {
            valueNode((ValueNode)statement.getIfExpression());
        }
        else if(statement.getIfExpression() instanceof OperationNode) {
            operation((OperationNode)statement.getIfExpression());
        }
        // check to see if there's a not
        if(statement.getIfExpression().getNot()) {
            asmCode.append("beq $t").append(currentRegister + 1);
            asmCode.append(", $zero, EndNum").append(++currentLabel).append("\n");
        }
        else {
            asmCode.append("bne $t").append(currentRegister + 1);
            asmCode.append(", $zero, ElseNum").append(++currentLabel).append("\n");
        }
        // check the instance type of then statement
        if(statement.getThenStatement() instanceof IfStatementNode) {
            ifStatement((IfStatementNode)statement.getThenStatement());
            // already incremented outside so you have to decrement
            currentLabel--;
        }
        else if(statement.getThenStatement() instanceof WhileStatementNode) {
            whileStatement((WhileStatementNode)statement.getThenStatement());
            // already incremented outside so you have to decrement
            currentLabel--;
        }
        else if(statement.getThenStatement() instanceof AssignmentStatementNode) {
            assignment((AssignmentStatementNode)statement.getThenStatement());
        }
        else if(statement.getThenStatement() instanceof CompoundStatementNode) {
            compoundStatement((CompoundStatementNode)statement.getThenStatement());
        }
        else if(statement.getThenStatement() instanceof ReadStatementNode) {
                read((ReadStatementNode)statement.getThenStatement());
        }
        else if(statement.getThenStatement() instanceof WriteStatementNode) {
            write((WriteStatementNode)statement.getThenStatement());
        }
        
        // add jump to the end
        asmCode.append("j EndNum").append(currentLabel).append("\n");
        asmCode.append("ElseNum").append(currentLabel).append(":\n");
        
        // check instance type of else statement
        if(statement.getElseStatement() instanceof IfStatementNode) {
            ifStatement((IfStatementNode)statement.getElseStatement());
            // already incremented outside so you have to decrement
            currentLabel--;
        }
        else if(statement.getElseStatement() instanceof WhileStatementNode) {
            whileStatement((WhileStatementNode)statement.getElseStatement());
            // already incremented outside so you have to decrement
            currentLabel--;
        }
        else if(statement.getElseStatement() instanceof AssignmentStatementNode) {
            assignment((AssignmentStatementNode)statement.getElseStatement());
        }
        else if(statement.getElseStatement() instanceof CompoundStatementNode) {
            compoundStatement((CompoundStatementNode)statement.getElseStatement());
        }
        else if(statement.getElseStatement() instanceof ReadStatementNode) {
                read((ReadStatementNode)statement.getElseStatement());
        }
        else if(statement.getElseStatement() instanceof WriteStatementNode) {
            write((WriteStatementNode)statement.getElseStatement());
        }
        // add end tag
        asmCode.append("EndNum").append(currentLabel++).append(":\n");
    }
    
    /**
     * produces assembly for while statements
     * @param statement 
     */
    public void whileStatement(WhileStatementNode statement) {
        asmCode.append("WhileNum").append(currentLabel).append(":\n");
        // check to see what instance is present
        // check expressions instance
        if(statement.getWhileExpression() instanceof VariableNode) {
            variableNode((VariableNode)statement.getWhileExpression());
        }
        else if(statement.getWhileExpression() instanceof OperationNode) {
            operation((OperationNode)statement.getWhileExpression());
        }
        else if(statement.getWhileExpression() instanceof ValueNode) {
            valueNode((ValueNode)statement.getWhileExpression());
        }
        // check to see if there's a not
        if(statement.getWhileExpression().getNot()) {
            asmCode.append("bne $t").append(currentRegister + 1);
            asmCode.append(", $zero, ExitNum").append(currentLabel++).append("\n");
        }
        else {
            asmCode.append("beq $t").append(currentRegister + 1);
            asmCode.append(", $zero, ExitNum").append(currentLabel++).append("\n");
        }
        
        // check statement instance
        if(statement.getDoStatement() instanceof IfStatementNode) {
            ifStatement((IfStatementNode)statement.getDoStatement());
            // already incremented outside so you have to decrement
            currentLabel--;
        }
        else if(statement.getDoStatement() instanceof WhileStatementNode) {
            whileStatement((WhileStatementNode)statement.getDoStatement());
            // already incremented outside so you have to decrement
            currentLabel--;
        }
        else if(statement.getDoStatement() instanceof CompoundStatementNode) {
            compoundStatement((CompoundStatementNode)statement.getDoStatement());
        }
        else if(statement.getDoStatement() instanceof AssignmentStatementNode) {
            assignment((AssignmentStatementNode)statement.getDoStatement());
        }
        else if(statement.getDoStatement() instanceof ReadStatementNode) {
                read((ReadStatementNode)statement.getDoStatement());
        }
        else if(statement.getDoStatement() instanceof WriteStatementNode) {
            write((WriteStatementNode)statement.getDoStatement());
        }
        asmCode.append("j WhileNum").append(currentLabel - 1).append("\n");
        asmCode.append("ExitNum").append(currentLabel - 1).append(":\n");
        currentLabel--;
    }
    /**
     * produces assembly for operations
     * @param operation 
     */
    public void operation(OperationNode operation) {
        // check to see what kind of instance you have
        // what do you see on the left?
        if(operation.getLeft() instanceof VariableNode) {
            variableNode((VariableNode)operation.getLeft());
        }
        else if(operation.getLeft() instanceof ValueNode) {
            valueNode((ValueNode)operation.getLeft());
        }
        else if(operation.getLeft() instanceof OperationNode) {
            operation((OperationNode)operation.getLeft());
        }

        currentRegister++;
        
        // check right side now.
        if(operation.getRight() instanceof VariableNode) {
            variableNode((VariableNode)operation.getRight());
        }
        else if(operation.getRight() instanceof ValueNode) {
            valueNode((ValueNode)operation.getRight());
        }
        else if(operation.getRight() instanceof OperationNode) {
            operation((OperationNode)operation.getRight());
        }
        
        // see what kind of operation you ran into
        if(operation.getOperation() == TokenType.MULTIPLY) {
            multiply(currentRegister - 1, currentRegister, currentRegister - 1);
        }
        else if(operation.getOperation() == TokenType.DIVIDE) {
            divide(currentRegister - 1, currentRegister, currentRegister - 1);
        }
        else if(operation.getOperation() == TokenType.PLUS) {
            plus(currentRegister, currentRegister - 1, currentRegister - 1);
        }
        else if(operation.getOperation() == TokenType.MINUS) {
            minus(currentRegister, currentRegister - 1, currentRegister - 1);
        }
        else if(operation.getOperation() == TokenType.LESS_THAN) {
            lessThan(currentRegister, currentRegister - 1, currentRegister);
        }
        else if(operation.getOperation() == TokenType.LESS_THAN_EQUALS) {
            lessThanEqual(currentRegister, currentRegister -1, currentRegister);
        }
        else if(operation.getOperation() == TokenType.GREATER_THAN) {
            greaterThan(currentRegister, currentRegister - 1, currentRegister);
        }
        else if(operation.getOperation() == TokenType.GREATER_THAN_EQUALS) {
            greaterThanEqual(currentRegister, currentRegister - 1, currentRegister);
        }
        else if(operation.getOperation() == TokenType.EQUALS) {
            equals(currentRegister, currentRegister - 1, currentRegister);
        }
        else if(operation.getOperation() == TokenType.LESS_THAN_GREATER_THAN) {
            notEqual(currentRegister, currentRegister - 1, currentRegister);
        }
        currentRegister--;
        
    }
    /**
     * produces assembly for compound statements
     * @param statements 
     */
    public void compoundStatement(CompoundStatementNode statements) {
         for(StatementNode statementNode: statements.getStatements()) {
            // first figure out what kind of instance you have
            // if you have an assignment node do the following
            if(statementNode instanceof AssignmentStatementNode) {
                // if you see an assignment statement
                assignment((AssignmentStatementNode)statementNode);
            }
            else if(statementNode instanceof IfStatementNode) {
                // if you see an if statement
                ifStatement((IfStatementNode)statementNode);
            }
            else if(statementNode instanceof WhileStatementNode) {
                // if you see a while statement
                whileStatement((WhileStatementNode)statementNode);
            }
            else if(statementNode instanceof CompoundStatementNode) {
                compoundStatement((CompoundStatementNode)statementNode);
            }
            else if(statementNode instanceof ReadStatementNode) {
                read((ReadStatementNode)statementNode);
            }
            else if(statementNode instanceof WriteStatementNode) {
                write((WriteStatementNode)statementNode);
            }
         }
    }
    /**
     * corresponding assembly for value nodes
     * @param value 
     */
    public void valueNode(ValueNode value) {
        asmCode.append("li $t").append(currentRegister).append(", ");
        asmCode.append(value.getAttribute());
        asmCode.append("\n");
    }
    /**
     * corresponding assembly for variable nodes
     * @param variable 
     */
    public void variableNode(VariableNode variable) {
        asmCode.append("lw $t").append(currentRegister).append(", ");
        asmCode.append(variable.getName()).append("\n");
    }
    /**
     * corresponding assembly for multiplication
     * @param rs
     * @param rt
     * @param store 
     */
    public void multiply(int rs, int rt, int store) {
        asmCode.append("mult $t").append(rs).append(", $t").append(rt).append("\n");
        asmCode.append("mflo $t").append(store).append("\n");
        
    }
    /**
     * corresponding assembly for division
     * @param rs
     * @param rt
     * @param store 
     */
    public void divide(int rs, int rt, int store) {
        asmCode.append("div $t").append(rs).append(", $t").append(rt).append("\n");
        asmCode.append("mflo $t").append(store).append("\n");
    }
    /**
     * corresponding assembly for addition
     * @param rs
     * @param rt
     * @param store 
     */
    public void plus(int rs, int rt, int store) {
        asmCode.append("add $t").append(store).append(", $t").append(rs).append(", $t").append(rt);
        asmCode.append("\n");
    }
    /**
     * corresponding assembly for subtraction
     * @param rs
     * @param rt
     * @param store 
     */
    public void minus(int rs, int rt, int store) {
        asmCode.append("sub $t").append(store).append(", $t");
        asmCode.append(rt).append(", $t").append(rs);
        asmCode.append("\n");
    }
    /**
     * corresponding assembly for comparing two values for less than
     * @param rs
     * @param rt
     * @param store 
     */
    public void lessThan(int rs, int rt, int store) {
        asmCode.append("slt $t").append(store).append(", $t");
        asmCode.append(rt).append(", $t").append(rs);
        asmCode.append("\n");
    }
    /**
     * corresponding assembly for comparing two values for greater than
     * @param rs
     * @param rt
     * @param store 
     */
    public void greaterThan(int rs, int rt, int store) {
        asmCode.append("sgt $t").append(store).append(", $t");
        asmCode.append(rt).append(", $t").append(rs);
        asmCode.append("\n");
    }
    /**
     * corresponding assembly for comparing if two values are equal
     * @param rs
     * @param rt
     * @param store 
     */
    public void equals(int rs, int rt, int store) {
        asmCode.append("beq $t").append(rs);
        asmCode.append(", $t").append(rt).append(", equalsOne\n");
        asmCode.append("li $t").append(rs);
        asmCode.append(", 0\n");
        asmCode.append("j endEqualsOne\n");
        asmCode.append("equalsOne:\n");
        asmCode.append("li $t").append(rs).append(", 1").append("\n");
        asmCode.append("endEqualsOne:\n");
    }
    /**
     * corresponding assembly for comparing two values for less than equal
     * @param rs
     * @param rt
     * @param store 
     */
    public void lessThanEqual(int rs, int rt, int store) {
        asmCode.append("sle $t").append(store).append(", $t");
        asmCode.append(rt).append(", $t").append(rs);
        asmCode.append("\n");
    }
    /**
     * corresponding assembly for comparing two values for greater than equal
     * @param rs
     * @param rt
     * @param store 
     */
    public void greaterThanEqual(int rs, int rt, int store) {
        asmCode.append("sge $t").append(store).append(", $t");
        asmCode.append(rt).append(", $t").append(rs);
        asmCode.append("\n");
    }
    /**
     * corresponding assembly for comparing two values for not equal
     * @param rs
     * @param rt
     * @param store 
     */
    public void notEqual(int rs, int rt, int store) {
        // nor them together
        asmCode.append("beq $t").append(rs);
        asmCode.append(", $t").append(rt).append(", equalsZero\n");
        asmCode.append("li $t").append(rs);
        asmCode.append(", 0\n");
        asmCode.append("j endEqualsZero\n");
        asmCode.append("equalsZero:\n");
        asmCode.append("li $t").append(rs).append(", 0").append("\n");
        asmCode.append("endEqualsZero:\n");
    }
    /**
     * asm code for reading in an integer
     * @param statement 
     */
    public void read(ReadStatementNode statement) {
        asmCode.append("li $v0, 5\n");
        asmCode.append("syscall\n");
        asmCode.append("sw $v0, ").append(statement.getId()).append("\n");
    }
    /**
     * corresponding assembly for writing
     * @param statement 
     */
    public void write(WriteStatementNode statement) {
        if(statement.getWriteExpression() instanceof VariableNode) {
            variableNode((VariableNode)statement.getWriteExpression());
        }
        else if(statement.getWriteExpression() instanceof OperationNode) {
            operation((OperationNode)statement.getWriteExpression());
        }
        else if(statement.getWriteExpression() instanceof ValueNode) {
            valueNode((ValueNode)statement.getWriteExpression());
        }
        // value is in current register
        asmCode.append("li $v0, $a1\n");
        asmCode.append("addi $a0, $t").append(currentRegister).append(", 0\n");
        asmCode.append("syscall\n");
    }
}
