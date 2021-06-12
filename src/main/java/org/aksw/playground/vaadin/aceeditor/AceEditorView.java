package org.aksw.playground.vaadin.aceeditor;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import de.f0rce.ace.AceEditor;
import de.f0rce.ace.enums.AceMode;
import de.f0rce.ace.enums.AceTheme;


@Route("")
@PWA(name = "AceEditor Test App", shortName = "Vaadin App",
        description = "AceEditor Test App", enableInstallPrompt = true)
@JsModule("@vaadin/vaadin-lumo-styles/presets/compact.js")
@Theme(value = Lumo.class)
@JsModule("@vaadin/vaadin-lumo-styles/badge.js")
public class AceEditorView extends AppLayout {

	private static final long serialVersionUID = 1L;

	public AceEditorView() {
        AceEditor textArea = new AceEditor();
        textArea.setWidthFull();
        textArea.setMode(AceMode.sparql);
        textArea.setTheme(AceTheme.chrome);
        textArea.setFontSize(18);
        setContent(textArea);
    }
}