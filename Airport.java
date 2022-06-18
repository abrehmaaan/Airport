import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Airport {
	private Queue<String> planeTakingOff;
	private Queue<String> planeLanding;
	private ArrayDeque<String> log;
	public Airport() {
		planeTakingOff = new LinkedList<String>();
		planeLanding = new LinkedList<String>();
		log = new ArrayDeque<String>();
	}
	public void addTakeOff(String flightNumber) {
		planeTakingOff.add(flightNumber);
	}
	public void addLanding(String flightNumber) {
		planeLanding.add(flightNumber);
	}
	public String handleNextAction() {
		if(!planeLanding.isEmpty()) {
			String flight = planeLanding.peek();
			log.add("Flight "+flight+" landed.");
			planeLanding.poll();
			return "Flight "+flight+" is landing.";
		}
		else if(!planeTakingOff.isEmpty()) {
			String flight = planeTakingOff.peek();
			log.add("Flight "+flight+" taken off.");
			planeTakingOff.poll();
			return "Flight "+flight+" is taking off.";
		}
		else {
			return "No plane is waiting to land or take off.";
		}
	}
	public String waitingPlanes() {
		String details="";
		if(!planeLanding.isEmpty()) {
			details+="Planes waiting for landing\n";
			details+="--------------------------\n";
			for (String flight: planeLanding) {
				details+=flight+"\n";
	        }
		}
		if(!planeTakingOff.isEmpty()) {
			details+="Planes waiting for take-off\n";
			details+="---------------------------\n";
			for (String flight: planeTakingOff) {
				details+=flight+"\n";
	        }
		}
		if(details.isEmpty()) {
			details+="No plane is in the landing and take-off queues.";
		}
		return details;
	}
	public String log() {
		String details="";
		if(!log.isEmpty()) {
			details+="List of the landing/take-off activities\n";
			details+="---------------------------------------\n";
			Iterator<String> s = log.descendingIterator();
			while(s.hasNext()) {
				details+=s.next()+"\n";
			}
		}
		else {
			details+="No activity exists.";
		}
		return details;
	}
	public void log(String filename) {
		if(!log.isEmpty()) {
			try {
				FileWriter file = new FileWriter(filename);
				System.out.println("Writing the airport log to the file...");
				Iterator<String> s = log.descendingIterator();
				while(s.hasNext()) {
					file.write(s.next()+"\n");
				}
				file.close();
				System.out.println("Done.");
			} catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		}
		else {
			System.out.println("No activity exists.");
		}
	}
}