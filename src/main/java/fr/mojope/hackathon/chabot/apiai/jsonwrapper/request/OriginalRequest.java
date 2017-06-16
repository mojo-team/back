package fr.mojope.hackathon.chabot.apiai.jsonwrapper.request;

public class OriginalRequest {
	
	public class Data {
		
		public class Sender {
			private String id;

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}
		}
		
		private Sender sender;

		public Sender getSender() {
			return sender;
		}

		public void setSender(Sender sender) {
			this.sender = sender;
		}
	}
	
	
	
	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
}
