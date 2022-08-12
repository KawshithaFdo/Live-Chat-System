package Server;

public class ServerFormController {

}
   /* public ServerFormController(Socket socket, ArrayList<ServerFormController> thread){
        this.accept = socket;
        this.thread = thread;
    }

    public void run() {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(accept.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                printWriter=new PrintWriter(accept.getOutputStream(),true);
                while (true){
                    String outputString = bufferedReader.readLine();

                    if(outputString.equals("exit")){
                        break;
                    }
                    for (ServerFormController s : thread) {
                        s.printWriter.println(outputString);
                    }
                    System.out.println("Server Success. " + outputString);
                }

            }catch (Exception e){
                System.out.println("Server Error" + e.getStackTrace());
            }
    }*/

