The ID class must be public
It must implement Serializable interface
It must have a no-argument constructor
It must override equals() and hashCode() methods.

@Override
public int hashCode() {
return Objects.hash(club, league);


@Override
public boolean equals(Object obj) 
if (this == obj) return true;
if (obj == null || getClass() != obj.getClass()) return false;
MatchStatisticsPk that = (MatchStatisticsPk) obj;
return Objects.equals(club, that.club) && Objects.equals(league, that.league);