//  Copyright 2011 by Sebastian Wagner
//
//  This file is part of motd.
//
//    motd is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//  motd is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with motd.  If not, see <http://www.gnu.org/licenses/>.
//

package org.zweizeichen.Motd.modules;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zweizeichen.Motd.Motd;

public class Rules implements CommandExecutor{

	private final Motd plugin;
	
	public Rules(Motd motd) {
        this.plugin = motd;  
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (plugin.checkPermissions("rules", sender)) {
			Player player = (Player) sender;
			return showRules(player);
		}
		
		return false;
	}
	
	// Rules function
		public boolean showRules (Player player) {
			if (plugin.config.getString("rules_enabled").equals("true")) {
				String rulesString;

				// Get rules from config
				rulesString = plugin.config.getString("rules");

				// Make newlines
				for (String motdStringSplitted : rulesString.split("<n>")) {
					// Clean \n and replace colors / placeholders
					player.sendMessage(plugin.replaceUmlaute(plugin.replacePlaceholders(plugin.replaceColors(motdStringSplitted.replaceAll("<n>", "")),player)));
				}
			}
			return true;
		}

}
