package dte.desktobeauty.utils;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;

public class SystemTrayBuilder
{
	private final SystemTray tray = SystemTray.getSystemTray();

	private Image image;
	private String tooltip;
	private PopupMenu menu;

	public SystemTrayBuilder withIcon(Image image) 
	{
		this.image = image;
		return this;
	}

	public SystemTrayBuilder withTooltip(String tooltip) 
	{
		this.tooltip = tooltip;
		return this;
	}

	public SystemTrayBuilder withMenuItem(String label, ActionListener clickHandler) 
	{
		if(this.menu == null)
			this.menu = new PopupMenu();

		MenuItem exitItem = new MenuItem(label);
		exitItem.addActionListener(clickHandler);
		this.menu.add(exitItem);

		return this;
	}

	public void display() throws AWTException 
	{
		TrayIcon icon = new TrayIcon(this.image, this.tooltip, this.menu);
		icon.setImageAutoSize(true);
		this.tray.add(icon);
	}
}
