package SyntaxTree;

import SimpleScanner.*;
import parser.SymbolTable.Kind;
/**
 * @author LinaVo
 */
public abstract class ExpressionNode extends SyntaxTreeNode {
    private boolean not;
    private TokenType sign;
    private Kind type;
    
    public void setNot(boolean value) {
        this.not = value;
    }
    public void setType(Kind type) {
        this.type = type;
    }
    public Kind getType() {
        return type;
    }
    public boolean getNot() {
        return not;
    }
    public void setSign(TokenType sign) {
        this.sign = sign;
    }
    public TokenType getSign(){
        return sign;
    }
    /**
     * for internal node
     * @param level
     * @return 
     */
    @Override
    public String indentedToString(int level) {
        String answer = super.indentedToString(level);
        answer += "ExpressionNode of type " + this.getType();
        return(answer);
    }
}
