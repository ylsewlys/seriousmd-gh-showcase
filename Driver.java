public class Driver{
    

    
    public static void main(String[] args){
 
        MCOView view = new MCOView();
        MCOModel model = new MCOModel();
        MCOController controller = new MCOController(view, model);
        
    }
}