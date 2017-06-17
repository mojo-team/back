package fr.mojope.hackathon.chabot.facebook.jsonwrapper;

import java.util.List;

import fr.mojope.hackathon.chabot.facebook.jsonwrapper.Element.Button;

public class FacebookResponseButton {
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
		
		public class Attachment {
			private String type = "template";
			private Payload payload;
			
			public class Payload {
				private String template_type = "button";
				private String text;
				private List<Button> buttons;
				public String getTemplate_type() {
					return template_type;
				}
				public void setTemplate_type(String template_type) {
					this.template_type = template_type;
				}
				public List<Button> getButtons() {
					return buttons;
				}
				public void setButtons(List<Button> buttons) {
					this.buttons = buttons;
				}
				public String getText() {
					return text;
				}
				public void setText(String text) {
					this.text = text;
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
