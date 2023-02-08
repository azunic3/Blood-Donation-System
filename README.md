My application is "Blood Donation System". The realization is pretty simple: I have 4 tables named:
Donor, Blood, Hospital and Patient. All connections between tables are 0..1:n or n:0..1.
Donor can donate 1 dose of blood in a certain period of time (in a 3/4 months) and there can be many donors that donate a dose at the same period of time.
The situation is similar when it comes to patients; the patient can receive one dose of blood in 3 months and there can be many patients in need. 
Blood bag can be stored in one blood bank (which is equal to Hospital in this diagram to make it more simple) and one blood bank/hospital can store many blood bags.
Patients can be treated in one hospital and one hospital can take care of many patients.
This ER diagram is just a simplified version of a real life system. I've managed to escape many to many connections, so there is no need for making relationship tables.
I have also skipped weak entities in order to make the simplest possible schema. 