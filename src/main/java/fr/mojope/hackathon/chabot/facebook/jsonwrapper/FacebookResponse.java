package fr.mojope.hackathon.chabot.facebook.jsonwrapper;

import java.util.List;

public class FacebookResponse {
	private Recipient recipient;
	private Message message;
	
	public class Recipient {
		private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}
	
	public class Message {
		private Attachment attachment;
		private String text;
		private List<QuickReply> quick_replies;
		
		public class Attachment {
			private String type = "template";
			private Payload payload;
			
			public class Payload {
				private String template_type = "generic";
				private List<Element> elements;
				public String getTemplate_type() {
					return template_type;
				}
				public void setTemplate_type(String template_type) {
					this.template_type = template_type;
				}
				public List<Element> getElements() {
					return elements;
				}
				public void setElements(List<Element> elements) {
					this.elements = elements;
				}
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public Payload getPayload() {
				return payload;
			}

			public void setPayload(Payload payload) {
				this.payload = payload;
			}
			
			
		}

		public Attachment getAttachment() {
			return attachment;
		}

		public void setAttachment(Attachment attachment) {
			this.attachment = attachment;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public List<QuickReply> getQuick_replies() {
			return quick_replies;
		}

		public void setQuick_replies(List<QuickReply> quick_replies) {
			this.quick_replies = quick_replies;
		}
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
