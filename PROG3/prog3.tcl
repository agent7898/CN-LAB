#Create Simulator
set ns [new Simulator]
#Open trace and NAM trace file
set tf [open pg3.tr w]
$ns trace-all $tf
set nf [open pg3.nam w]
$ns namtrace-all $nf
#Finish Procedure
proc finish {} {
global ns nf tf
#Dump all trace data and close the file
$ns flush-trace
close $nf
close $tf
exit 0
}
# Create nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]
set n6 [$ns node]
lappend nlist $n1 $n2 $n3 $n4 $n5 $n6

# create LAN and links
$ns make-lan $nlist 10Mb 10ms LL Queue/DropTail
$ns duplex-link $n0 $n1 10Mb 10ms DropTail
$ns duplex-link-op $n0 $n1 queuePos 0.5
#Set up a Transport layer connection.
set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0
set sink0 [new Agent/TCPSink]
$ns attach-agent $n3 $sink0
$ns connect $tcp0 $sink0
set tcp1 [new Agent/TCP]
$ns attach-agent $n2 $tcp1
set sink1 [new Agent/TCPSink]
$ns attach-agent $n1 $sink1
$ns connect $tcp1 $sink1
#Set up an Application layer Traffic
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0
$tcp0 attach $tf
$tcp0 trace cwnd_
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1
$tcp1 attach $tf
$tcp1 trace cwnd_
#Schedule events
$ns at 0.1 "$ftp0 start"
$ns at 0.2 "$ftp1 start"
$ns at 6.0 "finish"
# Start simulator
$ns run
