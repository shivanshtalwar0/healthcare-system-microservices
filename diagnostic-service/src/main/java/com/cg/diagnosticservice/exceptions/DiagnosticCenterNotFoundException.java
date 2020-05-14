package com.cg.diagnosticservice.exceptions;

public class DiagnosticCenterNotFoundException extends Exception {
    public DiagnosticCenterNotFoundException(String msg) {
        super("Diagnostic Center not found with id="+msg);
    }
}
