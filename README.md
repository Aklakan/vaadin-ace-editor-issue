# vaadin-ace-editor-issue


## Issue Description

Launching the main class [org.aksw.playground.vaadin.aceeditor.MainAppVaadinAceEditor](src/main/java/org/aksw/playground/vaadin/aceeditor/MainAppVaadinAceEditor) or running `mvn spring-boot:run` and visiting http://localhost:8000 yields the exception below.

The code of the actual view is in: [AceEditorView.java](src/main/java/org/aksw/playground/vaadin/aceeditor/AceEditorView.java).

According to the type hierarchy, AceEditor is a Component - so why does the exception indicate that it is not?

AceEditor < AbstractSinglePropertyField < AbstractField < com.vaadin.flow.component.Component


```
Caused by: java.lang.ArrayStoreException: de.f0rce.ace.AceEditor
	at org.aksw.playground.vaadin.aceeditor.AceEditorView.<init>(AceEditorView.java:34)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at org.springframework.beans.BeanUtils.instantiateClass(BeanUtils.java:204)
	... 75 more
```



I also got the stack trace below sometimes (not sure what exactly I did differently) - but essentially it seems to be a different presentation of the same problem: AceEditor on the byte code level is not considered a Component:


```
Caused by: java.lang.IllegalStateException: Failed to introspect Class [org.aksw.playground.vaadin.aceeditor.AceEditorView] from ClassLoader [org.springframework.boot.devtools.restart.classloader.RestartClassLoader@5dbb9daa]
	at org.springframework.util.ReflectionUtils.getDeclaredMethods(ReflectionUtils.java:481)
	at org.springframework.util.ReflectionUtils.doWithLocalMethods(ReflectionUtils.java:321)
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.determineCandidateConstructors(AutowiredAnnotationBeanPostProcessor.java:267)
	... 74 more
Caused by: java.lang.VerifyError: Bad type on operand stack
Exception Details:
  Location:
    org/aksw/playground/vaadin/aceeditor/AceEditorView.<init>()V @38: invokevirtual
  Reason:
    Type 'de/f0rce/ace/AceEditor' (current frame, stack[1]) is not assignable to 'com/vaadin/flow/component/Component'
  Current Frame:
    bci: @38
    flags: { }
    locals: { 'org/aksw/playground/vaadin/aceeditor/AceEditorView', 'de/f0rce/ace/AceEditor' }
    stack: { 'org/aksw/playground/vaadin/aceeditor/AceEditorView', 'de/f0rce/ace/AceEditor' }
  Bytecode:
    0x0000000: 2ab7 0001 bb00 0259 b700 034c 2bb6 0004
    0x0000010: 2bb2 0005 b600 062b b200 07b6 0008 2b10
    0x0000020: 12b6 0009 2a2b b600 0ab1               

	at java.lang.Class.getDeclaredMethods0(Native Method)
	at java.lang.Class.privateGetDeclaredMethods(Class.java:2701)
	at java.lang.Class.getDeclaredMethods(Class.java:1975)
	at org.springframework.util.ReflectionUtils.getDeclaredMethods(ReflectionUtils.java:463)
	... 76 more
```


