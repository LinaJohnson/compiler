package SyntaxTree;

/**
 *
 * @author LinaVo
 */
public class WhileStatementNode extends StatementNode{
    // private variables
    private ExpressionNode whileExpression;
    private StatementNode doStatement;

    /*
    * auto generated getters
    */
    public ExpressionNode getWhileExpression() {
        return whileExpression;
    }
    public StatementNode getDoStatement() {
        return doStatement;
    }
    
    /*
    * auto generated setters
    */
    public void setWhileExpression(ExpressionNode whileExpression) {
        this.whileExpression = whileExpression;
    }
    public void setDoStatement(StatementNode doStatement) {
        this.doStatement = doStatement;
    }
    
    /*
    * constructors for WhileStatementNode
    */
    public WhileStatementNode(ExpressionNode whileExpression, StatementNode doStatement) {
        this.whileExpression = whileExpression;
        this.doStatement = doStatement;
    }
    public WhileStatementNode() {
        this.whileExpression = new ExpressionNode(){};
        this.doStatement = new StatementNode(){};
    }
    
    /**
     * for leaf node
     * @param level
     * @return 
     */
    @Override
    public String indentedToString(int level) {
        String answer = super.indentedToString(level);
        answer += "WhileStatementNode: \n";
        answer += this.whileExpression.indentedToString(level + 1);
        answer += this.doStatement.indentedToString(level + 1);
        return(answer);
    }
}
