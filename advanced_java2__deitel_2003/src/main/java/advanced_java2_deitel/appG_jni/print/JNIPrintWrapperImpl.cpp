// JNIPrintWrapperImpl.cpp
// Implements the header created by java
// to integrate with JNI.

// C++ core header
#include <iostream.h>

// header produced by javah
#include "JNIPrintWrapper.h"

// prints the string given from java
JNIEXPORT void JNICALL Java_JNIPrintWrapper_printMessage
   ( JNIEnv * env, jobject thisObject, jstring message )
{
   // boolean to determine if string is copied
   jboolean copied;

   // call JNI method to convert jstring to cstring
   const char* charMessage =
       env->GetStringUTFChars( message, &copied );

   // print the message
   cout << charMessage;

   // release the string to prevent memory leak
   env->ReleaseStringUTFChars( message, charMessage );
}