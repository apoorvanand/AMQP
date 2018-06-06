package com.neotys.amqp.consume;

import com.neotys.action.argument.Arguments;
import com.neotys.action.argument.Option;
import com.neotys.amqp.common.AMQPAction;
import com.neotys.extensions.action.ActionParameter;
import com.neotys.extensions.action.engine.ActionEngine;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AMQPConsumeAction extends AMQPAction {

	private static final String DISPLAY_NAME = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault()).getString("consume.displayName");
	private static final String DISPLAY_PATH = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault()).getString("consume.displayPath");

	@Override
	public String getType() {
		return "AMQPConsume";
	}

	@Override
	public List<ActionParameter> getDefaultActionParameters() {
		final ArrayList<ActionParameter> parameters = new ArrayList<>();

		for (final AMQPConsumeParameter parameter : AMQPConsumeParameter.values()) {
			if (Option.AppearsByDefault.True.equals(parameter.getOption().getAppearsByDefault())) {
				parameters.add(new ActionParameter(parameter.getOption().getName(), parameter.getOption().getDefaultValue(),
						parameter.getOption().getType()));
			}
		}

		return parameters;
	}

	@Override
	public Class<? extends ActionEngine> getEngineClass() {
		return AMQPConsumeActionEngine.class;
	}

	private static final ImageIcon LOGO_ICON;

	static {
		final URL iconURL = AMQPConsumeAction.class.getResource("consume.png");
		if (iconURL != null) {
			LOGO_ICON = new ImageIcon(iconURL);
		} else {
			LOGO_ICON = null;
		}
	}

	@Override
	public Icon getIcon() {
		return LOGO_ICON;
	}

	@Override
	public boolean getDefaultIsHit() {
		return true;
	}

	@Override
	public String getDescription() {
		return "Publish a message on an AMQP channel.\n" + Arguments.getArgumentDescriptions(AMQPConsumeParameter.getOptions());
	}

	@Override
	public String getDisplayName() {
		return DISPLAY_NAME;
	}

	@Override
	public String getDisplayPath() {
		return DISPLAY_PATH;
	}
}
