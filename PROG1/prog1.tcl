#Create Simulator
set ns [new Simulator]

#Open Trace file and NAM file
set ntrace [open pg1.tr w]
$ns trace-all $ntrace

set namfile [open pg1.nam w]
$ns namtrace-all $namfile

#Finish Procedure
proc Finish {} {
global ns ntrace namfile

#Dump all the trace data and close the files
$ns flush-trace
close $ntrace
close $namfile
exit 0
}

#Create 3 nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

#Create Links between nodes
#You need to modify the bandwidth to observe the variation in packet drop
$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 200Kb 10ms DropTail # 600,400,200Kb vary sizes

#Set Queue Size
#You can modify the queue length as well to observe the variation in packet drop
$ns queue-limit $n0 $n1 10
$ns queue-limit $n1 $n2 10
$ns duplex-link-op $n1 $n2 queuePos 0.5

#Set up a Transport layer connection.
set udp [new Agent/UDP]
$ns attach-agent $n0 $udp
set null0 [new Agent/Null]
$ns attach-agent $n2 $null0
$ns connect $udp $null0

#Set up an Application layer Traffic
set cbr0 [new Application/Traffic/CBR]
$cbr0 set type_ CBR
$cbr0 set packetSize_ 100
$cbr0 set rate_ 1Mb
$cbr0 set random_ false
$cbr0 attach-agent $udp

#Schedule Events
$ns at 0.0 "$cbr0 start"
$ns at 5.0 "Finish"


#Run the Simulation
$ns run