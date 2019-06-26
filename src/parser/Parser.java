package parser;

import java.io.File;
import SimpleScanner.Scanner;
import SimpleScanner.TokenType;
import SimpleScanner.LookupTable;

import parser.*;
import parser.SymbolTable.*;
import SyntaxTree.*;
import java.util.ArrayList;

/**
 * @author LinaVo
 * Recursive decent. LL1.
 */
public class Parser {

    // instantiation variables
    private Scanner scanner;
    private TokenType currentToken;
    private SymbolTable symbolTable;
    
    /**
     * Creates an equation parser to parse the named file.
     * @param filename The name of the file to be parsed.
     */
    public Parser(String filename) {
        // instantiation variables
        File input = new File(filename);
        LookupTable symbols = new LookupTable();
        symbolTable = new SymbolTable();
        scanner = new Scanner(input);

        // Load in the first token as the lookahead token:
        scanner.nextToken();
        currentToken = scanner.getToken();
    }
    
    /** non-terminal program **/
    // program id ;
    // declarations
    // subprogram_declarations
    // compound_statement
    // .
    public ProgramNode program() {
        String programName="";
        // see program
        match(TokenType.PROGRAM);
        // see id
        if(scanner.getToken() == TokenType.ID){
            // see if lexeme is already in table
            if(!symbolTable.exists(scanner.getLexeme())) {
                // add PROGRAM to symbol table
                symbolTable.add(scanner.getLexeme(), Kind.PROGRAM);
                // get name of program node
                programName = scanner.getLexeme();
                match(TokenType.ID);
            }
            // if it already exists
            else {
                // throw an error, exit
                System.out.println("Error - " + scanner.getLexeme() + " already exists.");
                System.out.println(symbolTable.toString());
                System.exit(-1);
            }
        }
        // see ;
        match(TokenType.SEMICOLON);
        // create new program node
        ProgramNode program = new ProgramNode();
        program.setName(programName);
        program.setVariables(declarations());
        program.setFunctions(subprogram_declarations());
        program.setMain(compound_statement());
        // see . 
        match(TokenType.PERIOD);
        program.toString();
        return program;
    }
    
    
    /** non-terminal identifier_list **/
    // id |
    // id , identifier_list
    private ArrayList<VariableNode> identifier_list() {
        ArrayList<VariableNode> vars = new ArrayList<>();
        // see if id is in symbolTable
        if(symbolTable.exists(scanner.getLexeme())) {
            // if it's in there don't do anything.
            System.out.println("Error - " + scanner.getLexeme() + " already exists.");
            System.out.println(symbolTable.toString());
            System.exit(-1);
        }
        else {
            // add to symbolTable if it's not already in there.
            symbolTable.add(scanner.getLexeme(), Kind.VAR);
            // creating variable node
            VariableNode name = new VariableNode();
            name.setName(scanner.getLexeme());
            // appending variable node name into vars array list
            vars.add(name);
            // current token better be an ID
            match(TokenType.ID);
            // if the next token is a comma
            if(currentToken == TokenType.COMMA) {
                // see ,
                match(TokenType.COMMA);
                // non-terminal identifier_list
                vars.addAll(identifier_list());
            }
        }
        return vars;
    }
    
    
    /** non-terminal declarations **/
    // var identifier_list : type ; declarations |
    // lambda
    private DeclarationsNode declarations() {
        DeclarationsNode decNode = new DeclarationsNode();
        // see if token is VAR
        if(currentToken == TokenType.VAR) {
            // saw var match var
            match(TokenType.VAR);
            // Create Declarations Node
            decNode = new DeclarationsNode(identifier_list());
            // see :
            match(TokenType.COLON);
            // nonterminal type
            decNode.setType(type());
            // see ;
            match(TokenType.SEMICOLON);
            // don't forget to append the current list of vars!
            decNode.addVars(declarations().getVars());
        }
        // else do nothing.
        return decNode;
    }
    
    
    /** non-terminal type **/
    // standard_type |
    // array [ num : num ] of standard_type
    private TokenType type() {
        TokenType type;
        // make sure that you see an array
        if(currentToken == TokenType.ARRAY) {
            // see ARRAY
            // add array to symbol table
            symbolTable.add(scanner.getLexeme(), Kind.ARRAY);
            type = TokenType.ARRAY;
            match(TokenType.ARRAY);
            // see [
            match(TokenType.OPEN_BRACKET);
            // see num
            match(TokenType.INTEGER);
            // see :
            match(TokenType.COLON);
            // see num
            match(TokenType.INTEGER);
            // see ]
            match(TokenType.CLOSE_BRACKET);
            // see of
            match(TokenType.OF);
        }
        else {
            // if you don't see any array.
            // non-terminal standard_type
            type = standard_type();
        }
        return type;
    }
    
    /** non-terminal standard_type **/
    // integer |
    // real
    private TokenType standard_type() {
        TokenType tokenType = null;
        if(currentToken == TokenType.INTEGER) {
            tokenType = TokenType.INTEGER;
            // see an integer
            match(TokenType.INTEGER);
        }
        else if(currentToken == TokenType.REAL) {
            tokenType = TokenType.REAL;
            // see a real number
            match(TokenType.REAL);
        }
        return tokenType;
    }
    
    
    /** non-terminal subprogram_declarations **/
    // subprogram_declaration;
    // subprogram_declarations |
    // lambda
    private SubProgramDeclarationsNode subprogram_declarations() {
        SubProgramDeclarationsNode subprogram_decList = new SubProgramDeclarationsNode();
        if(currentToken == TokenType.FUNCTION || currentToken == TokenType.PROCEDURE) {
            // non-terminal subprogram_declaration
            subprogram_decList.add(subprogram_declaration());
            // see ;
            match(TokenType.SEMICOLON);
            subprogram_decList.addAll(subprogram_declarations());
        }
        return subprogram_decList;
    }
    
    
    /** non-terminal subprogram_declaration **/
    // subprogram_head
    // declarations
    // subprogram_declarations
    // compound_statement
    private SubProgramNode subprogram_declaration() {
        SubProgramNode subDecNode;
        ArrayList<DeclarationsNode> declarationsList = new ArrayList<>();
        ArrayList<SubProgramDeclarationsNode> subDecNodeList = new ArrayList<>();
        // setting subprogram declaration equal to the returned node from 
        // subprogram head
        subDecNode = subprogram_head();
        // setting variables
        declarationsList.add(declarations());
        subDecNode.setVariables(declarationsList);
        // setting subProgramDecs
        subDecNodeList.add(subprogram_declarations());
        subDecNode.setSubProgramDecs(subDecNodeList);
        // setting compound statement
        subDecNode.setCompoundStmts(compound_statement());
        // return node
        return subDecNode;
    }
    
    
    /** non-terminal subprogram_head **/
    // function id arguements : standard_type ; |
    // procedure id arguments ;
    private SubProgramNode subprogram_head(){
        SubProgramNode spn = null;
        
        if(currentToken == TokenType.FUNCTION) {
            //create new function node
            spn = new FunctionNode();
            // see function
            match(TokenType.FUNCTION);
            // make sure it's not in the symbol table
            if(!symbolTable.exists(scanner.getLexeme())) {
                // set id of function node to id
                spn.setId(scanner.getLexeme());
                // add FUNCTION to symbolTable
                symbolTable.add(scanner.getLexeme(), Kind.FUNCTION);
            }
            else {
                // it was in the symbolTable
                System.out.println("Error - " + scanner.getLexeme() + " already exists.");
                System.out.println(symbolTable.toString());
                System.exit(-1);
            }
            // see id
            match(TokenType.ID);
            // non-terminal arguments
            spn.setArguements(arguments());
            // see :
            match(TokenType.COLON);
            // non-terminal standard_type
            ((FunctionNode)spn).setStandard_type(standard_type());
            // see ;
            match(TokenType.SEMICOLON);
        }
        else if(currentToken == TokenType.PROCEDURE) {
            // create new procedure node
            spn = new ProcedureNode();
            // see procedure
            match(TokenType.PROCEDURE);
            // make sure it's not in the symbol table
            if(!symbolTable.exists(scanner.getLexeme())) {
                // set procedure node id to lexeme
                spn.setId(scanner.getLexeme());
                // add PROCEDURE to symbolTable
                symbolTable.add(scanner.getLexeme(), Kind.PROCEDURE);
            }
            else {
                // it was in the symbolTable
                System.out.println("Error - " + scanner.getLexeme() + " already exists.");
                System.out.println(symbolTable.toString());
                System.exit(-1);
            }
            // see id
            match(TokenType.ID);
            // non-terminal arguements
            spn.setArguements(arguments());
            // see ;
            match(TokenType.SEMICOLON);
        }
        return spn;
    }
    
    
    /** non-terminal arguments **/
    // ( parameter_list _ |
    // lambda
    private ArrayList<VariableNode> arguments() {
        // creating arraylist that holds all the variable nodes passed from
        // parameter_list()
        ArrayList<VariableNode> argumentList = new ArrayList<>();
        
        if(currentToken == TokenType.OPEN_PAREN) {
            // see (
            match(TokenType.OPEN_PAREN);
            /// adding all the variable nodes into argumentList
            argumentList.addAll(parameter_list());
            // see )
            match(TokenType.CLOSE_PAREN);
        }
        // return argumentList
        return argumentList;
    }
    
    
    /** non-terminal parameter_list **/
    // identifier_list : type |
    // identifier_list : type ; parameter_list
    private ArrayList<VariableNode> parameter_list() {
        ArrayList<VariableNode> peramsList = new ArrayList<>();
        if(currentToken == TokenType.ID) {
            // non-terminal identifier_list
            peramsList.addAll(identifier_list());
            // see :
            match(TokenType.COLON);
            // non-terminal type
            type();
            if(currentToken == TokenType.SEMICOLON) {
                // see ;
                match(TokenType.SEMICOLON);
                // non-terminal parameter_list
                peramsList.addAll(parameter_list());
            }
            // when not semicolon, exit if statement
        }
        return peramsList;
    }
    
    
    /** non-terminal compound_statement **/
    // begin optional_statements end
    private CompoundStatementNode compound_statement() {
        // see begin
        match(TokenType.BEGIN);
        // add statements to array list
        CompoundStatementNode compStNode = new CompoundStatementNode();
        // non-terminal optional_statements
        compStNode.setStatements(optional_statements());
        // see end
        match(TokenType.END);
        return compStNode;
    }
    
    /** non-terminal optional_statements **/
    // statement_list |
    // lambda
    private ArrayList<StatementNode> optional_statements() {
        
        ArrayList<StatementNode> statementList = new ArrayList<>();
        if(currentToken == TokenType.END) {
            // match token.
            // occurs in compound_statement()
            return statementList;
        }
        // add statement node to the array
        statementList.addAll(statement_list());
        // return the lsit of statmenet nodes
        return statementList;
    }
    
    
    /** non-terminal statement_list **/
    // statement |
    // statement ; statement_list
    private ArrayList<StatementNode> statement_list() {
        ArrayList<StatementNode> statements = new ArrayList<>();
        // non-terminal statement
        StatementNode stmt = statement();
        if(stmt != null) {
            statements.add(stmt);
        }
        if(currentToken == TokenType.SEMICOLON) {
            match(TokenType.SEMICOLON);
            // non-terminal statement_list
            ArrayList<StatementNode> tempStmtList = statement_list();
            statements.addAll(tempStmtList);
        }
        return statements;
    }
    
    
    /** non-terminal statement **/
    // variable assignop expression |
    // procedure_statement | (ignore for this semester)
    // compound_statement |
    // if expression then statement else statement |
    // while expression do statement |
    // read(id) (ignore for now)
    // write (expression) (ignore for now)
    private StatementNode statement() {
        
        StatementNode statement = null;
        
        if(currentToken == TokenType.IF) {
            statement = new IfStatementNode();
            // see if
            match(TokenType.IF);
            // non-terminal expression
            ((IfStatementNode)statement).setIfExpression(expression());
            // see then
            match(TokenType.THEN);
            // non-terminal statement
            ((IfStatementNode)statement).setThenStatement(statement());
            // see else
            match(TokenType.ELSE);
            // non-terminal statement
            ((IfStatementNode)statement).setElseStatement(statement());
        }
        else if(currentToken == TokenType.WHILE) {
            statement = new WhileStatementNode();
            // see while
            match(TokenType.WHILE);
            // non-terminal expression
            ((WhileStatementNode)statement).setWhileExpression(expression());
            // see do
            match(TokenType.DO);
            // // non-terminal statement
            ((WhileStatementNode)statement).setDoStatement(statement());
        }
        else if(currentToken == TokenType.BEGIN) {
            // see begin because compound statement starts with begin
            // if you see begin then you know to do this.
            statement = compound_statement();
        }
        else if(currentToken == TokenType.ID) {
            // make sure id is in symbol table
            if(symbolTable.exists(scanner.getLexeme())) {
                statement = new AssignmentStatementNode();
                VariableNode variable = new VariableNode();
                variable.setName(scanner.getLexeme());
                ((AssignmentStatementNode)statement).setValue(variable);
                // see id
                match(TokenType.ID);
                if(currentToken == TokenType.ASSIGN) {
                    // see assign
                    match(TokenType.ASSIGN);
                    // non-terminal expression
                    ((AssignmentStatementNode) statement).setExpression(expression());
                }
                else if(currentToken == TokenType.OPEN_BRACKET) {
                    // see [ then you know it is variable
                    // then do this
                    variable();
                }
                else if(currentToken == TokenType.OPEN_PAREN) {
                    // see ( then you know it is a procedure_statement
                    // do this
                    statement = procedure_statement();
                }
            }
            else {
                // it was not in the symbolTable
                System.out.println("Error - " + scanner.getLexeme() + " doesn't exist.");
                System.out.println(symbolTable.toString());
                System.exit(-1);
            }
        }
        else if(scanner.getLexeme().equals("read")) {
            match(TokenType.ID);
            match(TokenType.OPEN_PAREN);
            VariableNode id = new VariableNode();
            id.setName(scanner.getLexeme());
            statement = new ReadStatementNode(id);
            match(TokenType.CLOSE_PAREN);
        }
        else if(scanner.getLexeme().equals("write")) {
            match(TokenType.ID);
            match(TokenType.OPEN_PAREN);
            statement = new WriteStatementNode(expression());
            match(TokenType.CLOSE_PAREN);
        }
        
        return statement;
    }
    
    
    /** non-terminal variable **/
    // id |
    // id [ expression ]
    private VariableNode variable() {
        VariableNode variable = new VariableNode();
        if(symbolTable.exists(scanner.getLexeme())) {
            variable.setName(scanner.getLexeme());
            // already matched id in statement()
            // see [
            match(TokenType.OPEN_BRACKET);
            // non-terminal expression
            variable.setExpression(expression());
            // see ]
            match(TokenType.CLOSE_BRACKET);
        }
        else {
            System.out.println("Error - " + scanner.getLexeme() + " has not been declared.");
            System.out.println(symbolTable.toString());
            System.exit(-1);
        }
        return variable;
    }
    
    
    /** non-terminal procedure_statement **/
    // id |
    // id ( expression_list )
    private StatementNode procedure_statement(){
        // create necessary nodes
        ProcedureStatementNode procedure = new ProcedureStatementNode();
        // make sure variable is declared
        if(symbolTable.exists(scanner.getLexeme())){
            VariableNode id = new VariableNode();
            // set id in variable node to current lexeme
            id.setName(scanner.getLexeme());
            // pass variable node to procedure statement node
            procedure.setId(id);
            // already matched id in statement()
            // see (
            match(TokenType.OPEN_PAREN);
            // non-terminal expression_list
            procedure.setExpressionList(expression_list());
            // see )
            match(TokenType.CLOSE_PAREN);
        }
        else {
            System.out.println("Error - " + scanner.getLexeme() + " has not been declared.");
            System.out.println(symbolTable.toString());
            System.exit(-1);
        }
        return procedure;
    }
    
    
    /** non-terminal expression_list **/
    // expression |
    // expression , expression_list
    private ArrayList<ExpressionNode> expression_list() {
        ArrayList<ExpressionNode> expressionList = new ArrayList<>();
        // adding expression node into array
        expressionList.add(expression());
        
        if(currentToken == TokenType.COMMA) {
            // see ,
            match(TokenType.COMMA);
            // non-terminal expression_list
            expression_list();
        }
        return expressionList;
    }
    
    
    /** non-terminal expression **/
    // simple_expression |
    // simple_expression relop simple_expression
    private ExpressionNode expression(){
        ExpressionNode expression = simple_expression();

        // realop cases below.
        // =, <>, <, <=, >=, >
        if(currentToken == TokenType.EQUALS ||
           currentToken == TokenType.LESS_THAN_GREATER_THAN ||
           currentToken == TokenType.LESS_THAN ||
           currentToken == TokenType.LESS_THAN_EQUALS ||
           currentToken == TokenType.GREATER_THAN_EQUALS ||
           currentToken == TokenType.GREATER_THAN) {

            ExpressionNode tempNode = expression;
            // set operation node to the operation
            expression = new OperationNode(currentToken);
            // if you see any of these then match.
            match(currentToken);
            ((OperationNode)expression).setLeft(tempNode);
            ((OperationNode)expression).setRight(simple_expression());
        }
        return expression;
    }
    
    
    /** non-terminal simple_expression **/
    // term simple_part |
    // sign term simple_part
    private ExpressionNode simple_expression(){
        
        ExpressionNode leftOfOperation = term();
        ExpressionNode remainingOperation = simple_part();
        
        if(((OperationNode)remainingOperation).getOperation() != null) {
            ((OperationNode)remainingOperation).setLeft(leftOfOperation);
            if(currentToken == TokenType.PLUS || currentToken == TokenType.MINUS) {
            // if you see any of these then you know it's sign
            remainingOperation.setSign(sign());
            }
            return remainingOperation;
        }
        if(currentToken == TokenType.PLUS || currentToken == TokenType.MINUS) {
            leftOfOperation.setSign(sign());
        }
        return leftOfOperation;
    }
    
    
    /** non-terminal simple_part **/
    // addop term simple_part |
    // lambda
    private OperationNode simple_part() {
        OperationNode operation = new OperationNode();
        // see an addop
        // which is +, -, or
        if(currentToken == TokenType.PLUS ||
            currentToken == TokenType.MINUS ||
            currentToken == TokenType.OR) {
            // set operation of the operation node to the current operation
            operation.setOperation(currentToken);
            match(currentToken);
            // sett the right child to the expression node returned from term
            operation.setRight(term());
            operation.setLeft(simple_part());
        }
        return operation;
    }
    
    
    /** non-terminal term **/
    // factor term_part
    // lambda
    private ExpressionNode term() {
        // setting left child to the return of factor
        // seting rest of operation to the return of term part
        // which has the right child and operation node 
        ExpressionNode leftOfOperation = factor();
        ExpressionNode remainingOperation = term_part();
        
        if(((OperationNode)remainingOperation).getOperation() != null) {
            ((OperationNode)remainingOperation).setLeft(leftOfOperation);
            return remainingOperation;
        }
        return leftOfOperation;
    }
    
    
    /** non-terminal term_part **/
    // mulop factor term_part |
    // lambda
        private OperationNode term_part() {
        //create operation node
        OperationNode operation = new OperationNode();
        // see a mulop
        // mulop includes *, /, mod, and
        if(currentToken == TokenType.MULTIPLY ||
           currentToken == TokenType.DIVIDE ||
           currentToken == TokenType.MOD ||
           currentToken == TokenType.AND) {
            operation.setOperation(currentToken);
            // match the mulop
            match(currentToken);
            // non-terminal term_part
            operation.setRight(factor());
            operation.setLeft(term_part());
        }
        return operation;
    }
    
    
    /** non-terminal factor **/
    // id |
    // id [ expression ] |
    // id ( expression_list ) |
    // num |
    // ( expression ) |
    // not factor
    private ExpressionNode factor() {
        
        ExpressionNode currentExpressionNode = null;
        
        if(currentToken == TokenType.ID) {
            // see id
            // make sure id exists in symbol table
            if(symbolTable.exists(scanner.getLexeme())) {
                
                currentExpressionNode = new VariableNode();
                ((VariableNode)currentExpressionNode).setName(scanner.getLexeme());
                currentExpressionNode.setType(symbolTable.getKind(scanner.getLexeme()));
                match(TokenType.ID);
                // set variable node name to this

                if(currentToken == TokenType.OPEN_BRACKET) {
                    // see [
                    match(TokenType.OPEN_BRACKET);
                    // non-terminal expression
                    ((VariableNode)currentExpressionNode).setExpression(expression());
                    // see ]
                    match(TokenType.CLOSE_BRACKET);
                }
                else if(currentToken == TokenType.OPEN_PAREN) {
                    // see (
                    match(TokenType.OPEN_PAREN);
                    // non-terminal expression_list
                    ((VariableNode)currentExpressionNode).setExpressionList(expression_list());
                    // see )
                    match(TokenType.CLOSE_PAREN);
                }
            }
            else {
                // it was not in the symbolTable
                System.out.println("Error - " + scanner.getLexeme() + " doesn't exist.");
                System.out.println(symbolTable.toString());
                System.exit(-1);
            }
        }
        // see num
        else if (currentToken == TokenType.NUM) {
            currentExpressionNode = new ValueNode();
            ((ValueNode)currentExpressionNode).setAttribute(scanner.getLexeme());
            match(TokenType.NUM);
        }
        else if(currentToken == TokenType.OPEN_PAREN) {
            // see (
            match(TokenType.OPEN_PAREN);
            // non-terminal expression
            currentExpressionNode = expression();
            // see )
            match(TokenType.CLOSE_PAREN);
        }
        else if(currentToken == TokenType.NOT) {
            // see not
            match(TokenType.NOT);
            // non-terminal factor
            currentExpressionNode = factor();
            currentExpressionNode.setNot(true);
        }
        return currentExpressionNode;
    }
    
    
    /** non-terminal sign **/
    // + |
    // -
    private TokenType sign() {
        TokenType sign = null;
        // see +
        if(currentToken == TokenType.PLUS) {
            sign = TokenType.PLUS;
            match(TokenType.PLUS);
        }
        // see -
        else if(currentToken == TokenType.MINUS) {
            sign = TokenType.MINUS;
            match(TokenType.MINUS);
        }
        return sign;
    }
    
    
    /**
     * Handles an error.
     * Prints out the existence of an error and then exits.
     */
    private void error() {
        // should considere a line counter and a character counter 
        // to find out where an error occurred.
        System.out.println("An error occurred. Invalid " + currentToken + " found." );
        System.exit(-1);
    }

    
    private void match(TokenType tokenType) {
        if(currentToken == tokenType) {
            // no error
            if(scanner.nextToken()) {
                currentToken = scanner.getToken();
            }
        }
        else {
            // token does not match current token
            error();
        }
    }
}
       