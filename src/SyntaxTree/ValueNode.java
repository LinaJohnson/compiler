
package SyntaxTree;

/**
 *
 * @author LinaVo
 */
public class ValueNode extends ExpressionNode {
    
    // instantiation variable
    private String attribute;

    /**
     * auto generated getter
     * @return 
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * auto generated setter
     * @param attribute 
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    
    /**
     * constructor
     * @param attribute 
     */
    public ValueNode(String attribute) {
        this.attribute = attribute;
    }
    public ValueNode() {
        this.attribute = "";
    }
    
    /**
     * for leaf node
     * @param level
     * @return 
     */
    @Override
    public String indentedToString(int level) {
        String answer = super.indentedToString(level);
        answer += "ValueNode: " + this.attribute + "\n";
        return answer;
    }
}
