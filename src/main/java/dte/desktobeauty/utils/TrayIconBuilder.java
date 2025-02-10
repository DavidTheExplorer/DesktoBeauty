package dte.desktobeauty.utils;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;

public class TrayIconBuilder
{
	private Image image;
	private String tooltip;
	private PopupMenu menu;

	public TrayIconBuilder withImage(Image image)
	{
		this.image = image;
		return this;
	}

	public TrayIconBuilder withTooltip(String tooltip)
	{
		this.tooltip = tooltip;
		return this;
	}

	public TrayIconBuilder withMenuItem(String label, ActionListener clickHandler)
	{
		if(this.menu == null)
			this.menu = new PopupMenu();

		MenuItem item = new MenuItem(label);
		item.addActionListener(clickHandler);
		this.menu.add(item);
		return this;
	}

	public TrayIconBuilder withMenuItem(String label, Runnable clickHandler)
	{
		return withMenuItem(label, event -> clickHandler.run());
	}

	public void display() throws AWTException 
	{
		TrayIcon icon = new TrayIcon(this.image, this.tooltip, this.menu);
		icon.setImageAutoSize(true);

		SystemTray.getSystemTray().add(icon);
	}
}
