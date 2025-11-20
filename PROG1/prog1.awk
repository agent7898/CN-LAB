BEGIN {
count=0;
}
{
    if ($1=="d")
    {
        count++;
    }
}
END {
    print "number of packets dropped is";
    print count;
}