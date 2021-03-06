// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package pushnotifications.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.systemwideinterfaces.MendixRuntimeException;
import com.mendix.systemwideinterfaces.core.ISession;
import com.mendix.systemwideinterfaces.core.IUser;

public class ExecuteMicroflowInNewContext extends CustomJavaAction<Boolean>
{
	private String microflow;
	private IMendixObject contextObject;

	public ExecuteMicroflowInNewContext(IContext context, String microflow, IMendixObject contextObject)
	{
		super(context);
		this.microflow = microflow;
		this.contextObject = contextObject;
	}

	@Override
	public Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		Boolean microflowResult;
		IContext newContext = getContext().getSession().createContext();
		newContext.startTransaction();
		
		try {
			microflowResult = Core.<Boolean>execute(newContext, microflow, contextObject);  
		}
		catch (MendixRuntimeException e) {
			newContext.rollbackTransAction();
			throw new MendixRuntimeException("Error while executing microflow", e);
		}
		finally {
			newContext.endTransaction();
		}

		return microflowResult;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public String toString()
	{
		return "ExecuteMicroflowInNewContext";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
