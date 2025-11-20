BEGIN {
}
{
if($6=="cwnd_")
{
if ($2 == 0 && $4 == 3) printf("%4.2f\t%4.2f\t\n",$1,$7)>>”g1.txt”; # $1=time, $7=cwnd
size
if ($2 == 2 && $4 == 1) printf("%4.2f\t%4.2f\t\n",$1,$7)>>”g2.txt”;
}
}
END {
puts "DONE";
}
