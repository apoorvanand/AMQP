package com.neotys.amqp.deletequeue;

import java.util.ArrayList;
import java.util.List;

import com.neotys.action.argument.Arguments;
import com.neotys.action.argument.Option;
import com.neotys.amqp.common.AMQPAction;
import com.neotys.extensions.action.ActionParameter;
import com.neotys.extensions.action.engine.ActionEngine;

public class AMQPDeleteQueueAction extends AMQPAction {

	private static final String TYPE = "amqp-delete-queue";
	
	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public List<ActionParameter> getDefaultActionParameters() {
		final ArrayList<ActionParameter> parameters = new ArrayList<>();

		for (final AMQPDeleteQueueParameter parameter : AMQPDeleteQueueParameter.values()) {
			if (Option.AppearsByDefault.True.equals(parameter.getOption().getAppearsByDefault())) {
				parameters.add(new ActionParameter(parameter.getOption().getName(), parameter.getOption().getDefaultValue(),
						parameter.getOption().getType()));
			}
		}

		return parameters;
	}

	@Override
	public Class<? extends ActionEngine> getEngineClass() {
		return AMQPDeleteQueueEngine.class;
	}

	@Override
	public String getDescription() {
		return "Delete a Queue on an AMQP channel.\n" + Arguments.getArgumentDescriptions(AMQPDeleteQueueParameter.getOptions());
	}
}
