# CS5530
Database Systems - Spring 2016 University of Utah

In this project, I designed, implemented, and documented a database system for a Yelp/TripAdivor/Dianpinglike
system called “UTrack”.

The following set of events and queries can be performed:

1) Registration: Registration: a new user has to provide the appropriate information; he/she can pick
  a login-name and a password. The login name should be checked for uniqueness.
  
2) Visit: After registration, a user can record a visit to any POI (the same user may visit the same
  POI multiple times). Each user session (meaning each time after a user has logged into the system) may
  add one or more visits, and all visits added by a user in a user session are reported to him/her for the final
  review and confirmation, before they are added into the database.
  
3) New POI: The admin user records the details of a new POI.

4) Update POI: The admin user may update the information regarding an existing POI.

5) Favorite recordings: Users can declare a POI as his/her favoriate place to visit.

6) Feedback recordings: Users can record their feedback for a POI. We should record the date, the
  numerical score (0= terrible, 10= excellent), and an optional short text. No changes are allowed; only one
  feedback per user per POI is allowed.
  
7) Usefulness ratings: Users can assess a feedback record, giving it a numerical score 0, 1, or 2 (’useless’,
  ’useful’, ’very useful’ respectively). A user should not be allowed to provide a usefulness-rating for his/her
  own feedbacks.
  
8) Trust recordings: A user may declare zero or more other users as ‘trusted’ or ‘not-trusted’.

9) POI Browsing: Users may search for POIs, by asking conjunctive queries on the price (a range),
  and/or address (at CITY or State level), and/or name by keywords, and/or category. Your system should
  allow the user to specify that the results are to be sorted (a) by price, or (b) by the average numerical score
  of the feedbacks, or (c) by the average numerical score of the trusted user feedbacks.
  
10) Useful feedbacks: For a given POI, a user could ask for the top n most ‘useful’ feedbacks. The
  value of n is user-specified (say, 5, or 10). The ‘usefulness’ of a feedback is its average ‘usefulness’ score.
  
11) Visiting suggestions: Like most e-commerce websites, when a user records his/her visit to a POI
  ‘A’, your system should give a list of other suggested POIs. POI ‘B’ is suggested, if there exist a user ‘X’
  that visited both ‘A’ and ‘B’. The suggested POIs should be sorted on decreasing total visit count (i.e., most
  popular first); count only visits by users like ‘X’.
  
12) ‘Two degrees of separation’: Given two user names (logins), determine their ‘degree of separation’,
  defined as follows: Two users ‘A’ and ‘B’ are 1-degree away if they have both favorited at least one common
  POI; they are 2-degrees away if there exists an user ‘C’ who is 1-degree away from each of ‘A’ and ‘B’, AND
  ‘A’ and ‘B’ are not 1-degree away at the same time.
  
13) Statistics: At any point, a user may want to show
  • the list of the m (say m = 5) most popular POIs (in terms of total visits) for each category,
  • the list of m most expensive POIs (defined by the average cost per head of all visits to a POI) for each
    category
  • the list of m highly rated POIs (defined by the average scores from all feedbacks a POI has received)
    for each category
    
14) User awards: At random points of time, the admin user wants to give awards to the ‘best’ users;
  thus, the admin user needs to know:
  • the top m most ‘trusted’ users (the trust score of a user is the count of users ‘trusting’ him/her, minus
    the count of users ‘not-trusting’ him/her)
  • the top m most ‘useful’ users (the usefulness score of a user is the average ‘usefulness’ of all of his/her
    feedbacks combined)
