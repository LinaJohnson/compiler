package SyntaxTree;
import SimpleScanner.TokenType;
/**
 *
 * @author LinaVo
 */
public class OperationNode extends ExpressionNode {
    
    // instantiation variables
    private TokenType operation;
    private ExpressionNode left;
    private ExpressionNode right;

    /**
     * auto generated getter
     * @return 
     */
    public TokenType getOperation() {
        return operation;
    }
    /**
     * auto generated getter
     * @return 
     */
    public ExpressionNode getLeft() {
        return left;
    }
    /**
     * auto generated getter
     * @return 
     */
    public ExpressionNode getRight() {
        return right;
    }

    /**
     * auto generated setter
     * @param operation 
     */
    public void setOperation(TokenType operation) {
        this.operation = operation;
    }
    /**
     * auto generated setter
     * @param left 
     */
    public void setLeft(ExpressionNode left) {
        this.left = left;
    }
    /**
     * auto generated setter
     * @param right 
     */
    public void setRight(ExpressionNode right) {
        this.right = right;
    }

    /**
     * constructor
     * @param operation
     */
    public OperationNode(TokenType operation) {
        this.operation = operation;
        this.left = left;
        this.right = right;
    }
    public OperationNode() {
        this.operation = null;
        this.left = null;
        this.right = null;
    }
    
    /**
     * for internal node
     * @param level
     * @return 
     */
    @Override
    public String indentedToString(int level) {
        String answer = super.indentedToString(level);
        answer += "OperationNode: " + this.operation + "\n";
        answer += left.indentedToString(level + 1);
        answer += right.indentedToString(level + 1);
        return(answer);
    }
}
