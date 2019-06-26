package parser;
import java.util.Hashtable;
import java.util.Stack;
import SimpleScanner.*;
import java.util.Set;

/**
 * @author LinaVo
 */
public class SymbolTable {
    /** 
     * Enumeration to show what kind the lexeme is.
     * @param enum
    **/
    public enum Kind {
        // kinds listed in homework.
        PROGRAM,VAR,ARRAY,FUNCTION,PROCEDURE
    }
    /**
     * Enumeration of the various types in grammar.
     * @param enum
     */
    public enum Type {
        // types listed in grammar
        INTEGER, REAL
    }
    
    // Stack to store all the scopes.
    // specifying generic to ensure it returns a Kind.
    private Stack<Hashtable<String, Kind>> scopes;
    // not sure what to include for hashtable?
    private Hashtable <String, Kind> globalTable;
    
    /**
     * Instantiation of SymbolTable
     */
    public SymbolTable () {
        // instantiation the global hash table.
        globalTable = new Hashtable<String, Kind>();

        // creating a new stack for the different scopes.
        scopes = new Stack<Hashtable<String, Kind>>();
        scopes.push(globalTable);
    }
    /**
     * returns the size of the stack.
     * @return int
     */
    public int getTableSize(){
        return scopes.size();
    }
    /**
     * Pushes hashtable onto the scopes stack.
     */
    public void pushTable() {
        // pushing new hashtable onto the scope stack
        scopes.push(new Hashtable<String, Kind>());
    }
    /**
     * Pops hashtable off of the scopes stack
     */
    public void popTable() {
        // pop the scope off of the stack
        scopes.pop();
    }
    /**
     * Adds the lexeme and corresponding into the hashtable. 
     * @param name
     * @param Kind 
     */
    public void add(String name, Kind kind) {
        // add the lexeme and information to the scope at the top of the stack.
        scopes.peek().put(name, kind);
    }
    /**
     * Checks to see if the lexeme is already in the scope.
     * @param lexeme
     * @return boolean
     */
    public boolean exists(String lexeme) {
        // return true if the lexeme is either in any of the scopes that aren't
        // in the global scope or if it is in the global scope.
        return scopes.peek().containsKey(lexeme) || globalTable.containsKey(lexeme);
    }
    /**
     * Returns the kind of the lexeme.
     * @param lexeme
     * @return Kind
     */
    public Kind getKind(String lexeme) {
        // see if the lexeme is in the local scope
        if(scopes.peek().containsKey(lexeme)) {
            // if it is then return the kind.
            return scopes.peek().get(lexeme);
        }
        // otherwise it's in the global scope.
        // return the kind.
        return globalTable.get(lexeme);
    }
    /**
     * toString method for SymbolTable
     * @return String
     */
    public String toString(){
        String asciiTable = "";
        Set<String> tableKeys = scopes.peek().keySet();
        asciiTable+=("---------------------------------------");
        asciiTable+=("\nLexeme \t\t Kind");
        asciiTable+=("\n---------------------------------------\n");
        for(String lexeme: tableKeys){
            asciiTable+=(lexeme + " \t\t " + scopes.peek().get(lexeme) + "\n");
        }
        asciiTable+=("---------------------------------------");
        return asciiTable;
    }
}
