package org.ace.ws.model.nameText;

import org.ace.insurance.system.productinformation.Language;
import org.ace.insurance.system.productinformation.text.NameText;

public class NT001 {

	private String id;
	private String content;
	private String name;
	private Language language;
	private int version;

	public NT001() {
	}

	public NT001(NameText nameText) {
		id = nameText.getId();
		content = nameText.getContent();
		name = nameText.getName();
		language = nameText.getLanguage();
		version = nameText.getVersion();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
