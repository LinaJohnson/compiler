package SyntaxTree;

/**
 *
 * @author LinaVo
 */
public class WriteStatementNode extends StatementNode {
    // private variable
    private ExpressionNode writeExpression;
    /*
    * auto generated getter
    */
    public ExpressionNode getWriteExpression() {
        return writeExpression;
    }
    /*
    * auto generated setter
    */
    public void setWriteExpression(ExpressionNode writeExpression) {
        this.writeExpression = writeExpression;
    }
    /*
    * constructor for WriteStatementNode
    */
    public WriteStatementNode(ExpressionNode writeExpression) {
        this.writeExpression = writeExpression;
    }
    public WriteStatementNode() {
        this.writeExpression = new ExpressionNode(){};
        
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
        answer += this.writeExpression.indentedToString(level + 1);
        return(answer);
    }
}
