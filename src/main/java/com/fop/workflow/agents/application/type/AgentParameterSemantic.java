package com.fop.workflow.agents.application.type;

public enum AgentParameterSemantic {

    BODY_TEXT("bodyText"), 
    IMAGE_BASE64("image_format:base64", "^image.*"), 
    PATH_IMAGE_FILE("path_image", "^path.*"),
    PATH_TEXT_TXT_FILE("path_text_txt", "^path.*");

    private String semantic;
    private String regex;

    AgentParameterSemantic(String semantic) {
        this.semantic = semantic;
        regex = "a^"; // matches nothing
    }

    AgentParameterSemantic(String semantic, String regex) {
        this.semantic = semantic;
        this.regex = regex;
    }

    public String getSemantic() {
        return semantic;
    }

    public String getRegex() {
        return regex;
    }

}
