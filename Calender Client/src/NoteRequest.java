import com.google.gdata.data.DateTime;


public class NoteRequest  {

	String noteTitle;
	DateTime startDate;
	DateTime endDate;
	int timeRequired;
	
	public NoteRequest(String noteTitle, DateTime startDate, DateTime endDate,
			int timeRequired) {
		super();
		
		this.noteTitle = noteTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.timeRequired = timeRequired;	
	}

}
