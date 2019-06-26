package SyntaxTree;

/**
 *
 * @author LinaVo
 */
public class AssignmentStatementNode extends StatementNode {
   
    // instantiation variables
    private VariableNode lvalue;
    private ExpressionNode expression;
    
    /**
     * auto generated getter
     * @return 
     */
    public VariableNode getLvalue() {
        return lvalue;
    }
    public ExpressionNode getExpression() {
        return expression;
    }
    
    /**
     * auto generated setter
     * @param lvalue
     */
    public void setValue(VariableNode lvalue) {
        this.lvalue = lvalue;
    }
    public void setExpression(ExpressionNode expression) {
        this.expression = expression;
    }

    /**
     * constructor
     * @param lvalue
     * @param expression 
     */
    public AssignmentStatementNode(VariableNode lvalue, ExpressionNode expression) {
        this.lvalue = lvalue;
        this.expression = expression;
    }
    public AssignmentStatementNode() {
        this.lvalue = null;
        this.expression = null;
    }
    /**
     * for internal node
     * @param level
     * @return 
     */
    @Override
    public String indentedToString(int level) {
        String answer = super.indentedToString(level);
        answer += "AssignmentStatementNode\n";
        answer += this.lvalue.indentedToString(level + 1);
        answer += this.expression.indentedToString(level + 1);
        return(answer);
    }
}
