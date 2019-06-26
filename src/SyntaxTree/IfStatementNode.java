package SyntaxTree;

/**
 *
 * @author LinaVo
 */
public class IfStatementNode extends StatementNode {
    // private variables
    private ExpressionNode ifExpression;
    private StatementNode thenStatement;
    private StatementNode elseStatement;

    /*
    * auto generated getters
    */
    public ExpressionNode getIfExpression() {
        return ifExpression;
    }
    public StatementNode getThenStatement() {
        return thenStatement;
    }
    public StatementNode getElseStatement() {
        return elseStatement;
    }

    /*
    * auto generated setters
    */
    public void setIfExpression(ExpressionNode ifExpression) {
        this.ifExpression = ifExpression;
    }
    public void setThenStatement(StatementNode thenStatement) {
        this.thenStatement = thenStatement;
    }
    public void setElseStatement(StatementNode elseStatement) {
        this.elseStatement = elseStatement;
    }
    /*
    * constructors
    */
    public IfStatementNode(ExpressionNode ifExpression, StatementNode thenStatement, StatementNode elseStatement) {
        this.ifExpression = ifExpression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }
    public IfStatementNode() {
        this.ifExpression = new ExpressionNode(){};
        this.thenStatement = new StatementNode(){};
        this.elseStatement = new StatementNode(){};        
    }
    /**
     * for leaf node
     * @param level
     * @return 
     */
    @Override
    public String indentedToString(int level) {
        String answer = super.indentedToString(level);
        answer += "IfStatementNode: \n";
        answer += this.ifExpression.indentedToString(level + 1);
        answer += this.thenStatement.indentedToString(level + 1);
        answer += this.elseStatement.indentedToString(level + 1);
        return(answer);
    }
}
