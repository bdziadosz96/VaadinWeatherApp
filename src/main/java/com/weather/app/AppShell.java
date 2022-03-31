package com.weather.app;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@PWA(name = "Project Base for Vaadin", shortName = "Project Base")
@Theme(themeClass = Lumo.class, variant = Lumo.DARK)
public class AppShell implements AppShellConfigurator {
}
