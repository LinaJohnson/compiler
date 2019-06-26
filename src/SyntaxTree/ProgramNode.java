package SyntaxTree;
/**
 *
 * @author LinaVo
 */
public class ProgramNode extends SyntaxTreeNode {
    // instantiation variables
    private String name;
    private DeclarationsNode variables;
    private CompoundStatementNode main;
    private SubProgramDeclarationsNode functions;

    /**
     * auto generated getter
     * @return 
     */
    public SubProgramDeclarationsNode getFunctions() {
        return functions;
    }
    /**
     * auto generated getter
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * auto generated getter
     * @return 
     */
    public DeclarationsNode getVariables() {
        return variables;
    }
    /**
     * auto generated getter
     * @return 
     */
    public CompoundStatementNode getMain() {
        return main;
    }
    
    /**
     * auto generated setter
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * auto generated setter
     * @param variables 
     */
    public void setVariables(DeclarationsNode variables) {
        this.variables = variables;
    }
    /**
     * auto generated setter
     * @param main 
     */
    public void setMain(CompoundStatementNode main) {
        this.main = main;
    }
    /**
     * auto generated setter
     * @param functions 
     */
    public void setFunctions(SubProgramDeclarationsNode functions) {
        this.functions = functions;
    }
    
    /**
     * constructor
     * @param name
     * @param variables
     * @param main
     * @param functions 
     */
    public ProgramNode(String name, DeclarationsNode variables, CompoundStatementNode main, SubProgramDeclarationsNode functions) {
        this.name = name;
        this.variables = variables;
        this.main = main;
        this.functions = functions;
    }
    public ProgramNode() {
        this.name = "";
        this.variables = new DeclarationsNode();
        this.main = new CompoundStatementNode();
        this.functions = new SubProgramDeclarationsNode();
    }
    
    /**
     * for internal node
     * @param level
     * @return 
     */
    @Override
    public String indentedToString(int level) {
        String answer = super.indentedToString(level);
        answer += "ProgramNode: " + this.name + "\n";
        answer += this.variables.indentedToString(level + 1);
        answer += this.functions.indentedToString(level + 1);
        answer += this.main.indentedToString(level + 1);
        return(answer);
    }
}
