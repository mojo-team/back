package fr.mojope.hackathon.chabot.facebook.jsonwrapper;

import java.util.List;

public class Element {
	private String title;
	private String image_url;
	private String subtitle;
	private DefaultAction default_action;
	private List<Button> buttons;
	
	public class DefaultAction {
		private String type;
		private String url;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
		
	}
	
	public class Button {
		private String type;
		private String url;
		private String title;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public DefaultAction getDefault_action() {
		return default_action;
	}

	public void setDefault_action(DefaultAction default_action) {
		this.default_action = default_action;
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}
	
	
}
