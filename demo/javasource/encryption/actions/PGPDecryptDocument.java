// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package encryption.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;
import encryption.pgp.PGPFileProcessor;
import encryption.proxies.microflows.Microflows;

/**
 * Encrypt the FileDocument using PGP encryption. 
 *  This is allowed to be the same FileDocument instance and the action will just store th decrypted file in the entity.
 * 
 * The certificate must be a File containing a valid PGP key ring (matching the document) and the certificate must have a passphrase entered in the attribute
 * 
 * This action will either return true or an exception
 */
public class PGPDecryptDocument extends CustomJavaAction<java.lang.Boolean>
{
	private IMendixObject __PrivateDecryptionKey;
	private encryption.proxies.PGPCertificate PrivateDecryptionKey;
	private IMendixObject __DocumentToDecrypt;
	private system.proxies.FileDocument DocumentToDecrypt;
	private IMendixObject __OutputDocument;
	private system.proxies.FileDocument OutputDocument;

	public PGPDecryptDocument(IContext context, IMendixObject PrivateDecryptionKey, IMendixObject DocumentToDecrypt, IMendixObject OutputDocument)
	{
		super(context);
		this.__PrivateDecryptionKey = PrivateDecryptionKey;
		this.__DocumentToDecrypt = DocumentToDecrypt;
		this.__OutputDocument = OutputDocument;
	}

	@Override
	public java.lang.Boolean executeAction() throws Exception
	{
		this.PrivateDecryptionKey = __PrivateDecryptionKey == null ? null : encryption.proxies.PGPCertificate.initialize(getContext(), __PrivateDecryptionKey);

		this.DocumentToDecrypt = __DocumentToDecrypt == null ? null : system.proxies.FileDocument.initialize(getContext(), __DocumentToDecrypt);

		this.OutputDocument = __OutputDocument == null ? null : system.proxies.FileDocument.initialize(getContext(), __OutputDocument);

		// BEGIN USER CODE
		PGPFileProcessor p = new PGPFileProcessor();
		p.setInputFileDocument(this.DocumentToDecrypt.getMendixObject());
		p.setOutputFileDocument(this.OutputDocument.getMendixObject());
		p.setPassphrase(Microflows.decrypt(getContext(), this.PrivateDecryptionKey.getPassPhrase_Encrypted()));
		p.setSecretKeyFileName(this.PrivateDecryptionKey.getMendixObject());

		return p.decrypt(getContext());

		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public java.lang.String toString()
	{
		return "PGPDecryptDocument";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
