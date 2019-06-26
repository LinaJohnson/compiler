package SyntaxTree;

import java.util.ArrayList;

/**
 *
 * @author LinaVo
 */
public class VariableNode extends ExpressionNode {
    
    // instantiation variable
    private String name;
    private ExpressionNode expression;
    private ArrayList<ExpressionNode> expressionList;

    /**
     * auto generated getter
     * @return 
     */
    public String getName() {
        return name;
    }
    public ExpressionNode getExpression() { return expression; }
    public ArrayList<ExpressionNode> getExpressionList() { return expressionList; }
    /**
     * auto generated setter
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    public void setExpression(ExpressionNode expression) { this.expression = expression; }
    public void setExpressionList(ArrayList<ExpressionNode> expressionList) {
        this.expressionList = expressionList;
    }

    /**
     * constructor
     * @param name 
     */
    public VariableNode(String name, ExpressionNode expression, ArrayList<ExpressionNode> expressionList) {
        this.name = name;
        this.expression = expression;
        this.expressionList = expressionList;
    }
    public VariableNode() {
        this.name = "";
        this.expression = null;
        this.expressionList = null;
    }
    
    /**
     * for leaf node
     * @param level
     * @return 
     */
    @Override
    public String indentedToString(int level) {
        String answer = super.indentedToString(level);
        answer += "VariableNode: " + this.name + "\n";
        return answer;
    }
}
