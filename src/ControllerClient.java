import com.app.db.model.Consultation;
import com.app.db.model.SocketRequest;
import com.app.db.model.User;
import com.app.service.ComunicationService;

public class ControllerClient {
	
	public static void main(String[] args){
		ComunicationService com = ComunicationService.getInstance();
		
		User u = new User();
		u.setUsername("danny");
		
		String typeOfRequest = "1";
		Consultation c = new Consultation();
		
		SocketRequest request = new SocketRequest(u, typeOfRequest, c, true);
		com.sendRequest(request);
		request.setNeedsResponse(false);
		com.sendRequest(request);
	}
}
