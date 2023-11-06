// JNICallMethod.cpp
// Calls methods on the PIContainer object sent to it.
// Creates new Java objects.

// standard c header
#include <iostream.h>

// header produced by javah
#include "JNIPIWrapper.h"

// retrieves value of PI based on passed PIContainer.
JNIEXPORT jdouble JNICALL Java_JNIPIWrapper_getPI__LPIContainer_2
(JNIEnv * env, jobject thisObject, jobject containerObject)
{
   // retrieve PIContainer class
   jclass PIClass = env->GetObjectClass( containerObject );

   // retrieve method ID of getPI
   jmethodID mid = env->GetMethodID( PIClass, "getPI", "()D" );

   // call method getPI of class PIContainer with no arguments
   jdouble PI = env->CallDoubleMethod( containerObject, mid, NULL );

   return PI;
}

// retrives value of PI by creating new PIContainer
JNIEXPORT jdouble JNICALL Java_JNIPIWrapper_getPI__
  (JNIEnv * env, jobject thisObject) {

   // finds Class PIContainer
   jclass PIClass = env->FindClass( "PIContainer" );

   // retrieves ID of constructor
   jmethodID constructorID =
      env->GetMethodID( PIClass, "<init>", "()V" );

   // creates new PIContainer
   jobject newContainer =
       env->NewObject( PIClass, constructorID, NULL );

   // retrieves method ID of getPI
   jmethodID mid = env->GetMethodID( PIClass, "getPI", "()D" );

    // call method getPI of class PIContainer with no arguments
   jdouble PI = env->CallDoubleMethod( newContainer, mid, NULL );

   return PI;
}