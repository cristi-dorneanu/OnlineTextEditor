package com.texteditor.model.config;

public enum Resource {
	login("/login"),
	signup("/signup"),
	textedit("/textedit"),
	manage("/manage"),
	view("/view-file"),
	edit("/edit-file"),
	delete("/delete-file");
	
	private static final String app = "/OnlineTextEditorWork";
	private String resource;
	
	private Resource(String resource) {
		this.resource = (resource == null) ? "" : resource.trim();
	}
	
	public String url() {
		return app + resource;
	}
	
	public String resource() {
		return resource;
	}
}
