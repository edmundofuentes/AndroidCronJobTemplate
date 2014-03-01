Android Notifier
================
A very basic template to create background cronjobs in Android, with a built-in one-time configuration view.

Overview
--------

Coming from an Unix background, powerful machines in our pockets with access to your position

Settings & Parameters
---------------------
Execution time --> PollReceiver PERIOD

Permissions
-----------
- Wake
- Auto boot

Extras:
Internet & GPS, since most tasks

# License / Libraries / Acknowledgement
Commonsware for their WakefulService, that is the base of this

## Base Structure
- **ScheduledServiceNotifier** que es un activity, que segun entiendo es el que va a poner el iconito en el launcher y va a pedir permisos, blah blah
- **ScheduledService** es un servicio que es el que se llama con la alarma para ejecutar el doWakefulWork()
- **PollReceiver** que es el que hace las alarmas y esta esperando recibirlas


######Last updated: Feb 2014