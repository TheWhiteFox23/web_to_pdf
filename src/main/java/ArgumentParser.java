/**
 * Responsible for argument parsing
 */
public class ArgumentParser {
    private String[] args;

    public ArgumentParser(String[] args){
        this.args = args;
    }

    public ArgumentParser(){
        this.args = new String[0];
    }

    /**
     * Find value to the given key in args array
     * @param key
     * @return String representation of the value or null
     */
    public String getValue(String key){
        for(int i = 0; i< args.length; i++){
            if(args[i].toLowerCase().equals(key.toLowerCase()) && i+1< args.length){
                return args[i+1];
            }
        }
        return null;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
