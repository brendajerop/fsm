import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

 
public class FSM {
 
    private enum State {
        Ready(true, "Deposit", "Quit"),
        Waiting(true, "Select", "Refund"),
        Dispensing(true, "Remove"),
        Refunding(false, "Refunding"),
        Exiting(false, "Quiting");
    	
	    final List<String> inputs;
        final boolean explicit;

        //state has explicit property and transitions
        State(boolean exp, String... in) {
            inputs = Arrays.asList(in);
            explicit = exp;
        }
 
    
      
        final static Map<String, State> map = new HashMap<>();
 
        static {
        	//map defines next state based on input
            map.put("Deposit", State.Waiting);
            map.put("Quit", State.Exiting);
            map.put("Select", State.Dispensing);
            map.put("Refund", State.Refunding);
            map.put("Remove", State.Ready);
            map.put("Refunding", State.Ready);
        }
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        State state = State.Ready;
 
        while (state != State.Exiting) {
            System.out.println(state.inputs);
            if (state.explicit){
                System.out.print("> ");
                state = state.map.get(sc.nextLine().trim());
            } else {
            	 //System.out.println(state.inputs.size());
                state = state.map.get(state.inputs.get(0));
               
            }
        }
    }
}