package hudson.maven;
import jenkins.model.Jenkins
import hudson.model.*
import hudson.*;
import hudson.slaves.*;
import hudson.maven.*;
import hudson.tasks.*;
import hudson.util.*;

//def hudson = hudson.model.Hudson.instance
//def jobname = hudson.getJob("EXECUTE-JOB")
//def workspace = jobname.getWorkspace()
//def scm_location = jobname.scm.locations[0]
//def name_of_the_job = jobname.getName()
//def jdk_of_the_job = jobname.getJDK()
//println("Workspace of given job:" +workspace)
//println("SCM location of given job:" +scm_location)

//def Pattern = build.envVars.jobPattern
//println("Job pattern given:" +Pattern)
//def jobPattern = Pattern
//def matchedJobs = Jenkins.instance.items.findAll{job->
//job.name=~/$jobPattern/
//}





//def option = build.envVars.SelectOption
//if(option == "listAll"){
//File file = new File(workspace.toString()+"/JobPatternList1.txt")
//FileWriter fw = new FileWriter(file,true)
//BufferedWriter bw = new BufferedWriter(fw)


//String Headings = "All jobs name present in the jenkin"
//println(Headings)
//bw.write(Headings)
//def listAllJobName = jenkins.model.Jenkins.instance.items.each{
//String jobNames= it.name}
//for(int i=0;i<listAllJobName.size();i++){
//println(listAllJobName.getAt(i).getName())
//bw.write(listAllJobName.getAt(i).getName().toString()+"Jobs workspace:"+matchedJobs.getAt(i).getWorkspace().toString())
//bw.newLine()
//}
//bw.close()
//}

//else if(option == "renameJob"){
//def newJobName = build.envVars.newJobName
//for(int i=0;i<matchedJobs.size();i++){
//def name = matchedJobs.getAt(i).getName() 
//println("************"+name)
//String[] splitName = name.split("-")
//for(int j=0;j<splitName.size();j++){
//if(splitName[j]=="CI"){
//matchedJobs.getAt(i).renameTo(newJobName+"-CI")
//matchedJobs.getAt(i).save()
//break;
//}else if(splitName[j]=="DEPLOY"){
//matchedJobs.getAt(i).renameTo(newJobName+"-DEPLOY")
//matchedJobs.getAt(i).save()
//break;
//}
//else if(splitName[j]=="DEPLOY_SNAPSHOT"){
//matchedJobs.getAt(i).renameTo(newJobName+"-DEPLOY_SNAPSHOT")
//matchedJobs.getAt(i).save()
//break;
//}
//else if(splitName[j]=="SONAR"){
//matchedJobs.getAt(i).renameTo(newJobName+"-SONAR")
//matchedJobs.getAt(i).getSonar
//matchedJobs.getAt(i).save()
//break;
//}
//else if(splitName[j]=="CONFIG"){
//j++;
//if(splitName[j]=="DEPLOY"){
//matchedJobs.getAt(i).renameTo(newJobName+"-CONFIG-DEPLOY")
//matchedJobs.getAt(i).save()
//break;
//}else{
//matchedJobs.getAt(i).renameTo(newJobName+"-CONFIG-DEPLOY_SNAPSHOT")
//matchedJobs.getAt(i).save()
//break;
//}
//}
//else{
//continue;
//}
//}
//}
//}
//if(matchedJobs.size()==0){
//println("Job is not available for the given job pattern.")
//}

//else if(option == "disable"){

//println("The below jobs are successfully disabled")
//for(int i=0;i<matchedJobs.size();i++){
//matchedJobs.getAt(i).disable()
//println(matchedJobs.getAt(i).getName())
//println(build.envVars)
//}
//}

//else if(option == "enable"){
//println("The below jobs are successfully Enabled")
//for(int i=0;i<matchedJobs.size();i++){
//matchedJobs.getAt(i).enable()
//println(matchedJobs.getAt(i).getName())
//}
//}

//else if(option == "sonar"){
//jobname.sonar.branch="FBMN"
//println("Sonar branch name is"+jobname.sonar.branch)
//}

//else{
//println("Result:"+ "No job is found based on your job Pattern")
//}

/*jobsChanged = new java.util.ArrayList()
jobsNotChanged = new java.util.ArrayList()
String threshold = "SUCCESS"
for(project in Hudson.instance.items) {
  new_builder = null
  old_builder = null
  
  for (builder in project.buildersList) {
    if (builder instanceof hudson.tasks.Shell) {
	 new_command = builder.command.replace("**************Shell script************", "hell")
	 if(new_command=="hell"){
	 new_builder = new hudson.tasks.Shell(new_command)
	 old_builder = builder;
    jobsChanged.add "$project.name"

  if (new_builder != null) {
   // comment out below for test run
    project.buildersList.add(new_builder)
	triggeredJob = hudson.model.Hudson.instance.items.find{job -> job.name == "$project.name"}
	triggeredJob.newBuild()
    project.buildersList.remove(old_builder)
	}else{
	continue;
	}
  }else{
  jobsNotChanged.add "$project.name"
  }
}

}
}
println ""
println "Jobs changed"
println ""

jobsChanged.each { line -> println line }

println ""
println "Jobs not changed"
println ""

jobsNotChanged.each { line -> println line }

println ""

""


println("parameter value:"+build.environment.get('jobUrl'));*/

jobsChanged = new java.util.ArrayList()
jobsNotChanged = new java.util.ArrayList() 
def option = build.envVars.SelectOption

if(option == "Single_job"){
for(project in Hudson.instance.items) {
if("$project.name"==build.environment.get('jobName')){
println("$project.name");
def hudson = hudson.model.Hudson.instance
def jobname = hudson.getJob("$project.name")
List<Builder> blist = new ArrayList<Builder>();
if (jobname instanceof hudson.maven.MavenModuleSet) {
  listener.getLogger().println("Build type: hudson.maven.MavenModuleSet");
  DescribableList<Builder, Descriptor<Builder>> dl = jobname.getPrebuilders();
  Collection<Builder> c = dl.toMap().values();
  listener.getLogger().println("valuesss"+dl.toMap().values().toString());
  blist.addAll(c);
  }
  for(Builder b : blist){
  listener.getLogger().println("value"+b.getCommand());

}

}
}
}
if(option == "JobPattern_job"){
def patternJob = build.envVars.jobPattern;
def jobPattern = patternJob;

def matchedJobs = Jenkins.instance.items.findAll{ job ->
job.name =~ /$jobPattern/
}

matchedJobs.each{ job ->
def hudson = hudson.model.Hudson.instance;
def jobname = hudson.getJob(job.name)

/*List<Builder> blist = new java.util.ArrayList<Builder>();
List<Builder> dlist = new java.util.ArrayList<Builder>();*/
if(jobname instanceof hudson.maven.MavenModuleSet){

DescribableList<Builder,Descriptor<Builder>> dl = jobname.getPrebuilders();
if(dl){
/*
Collection<Builder> c = dl.toMap().values();
blist.addAll(c);*/
}else{

println("This project is not having execute shell: "+ jobname.getName());
DescribableList<Builder,Descriptor<Builder>> bl = jobname.getPrebuilders();
bl.add(new hudson.tasks.Shell("hey buddy"));
/*
Collection<Builder> e = bl.toMap().values();
dlist.addAll(e)*/
}
}


else{
println("This project is not Maven Module set"+jobname);
}
}
/*for(Builder b : blist){
listener.getLogger().println("value"+b.getCommand());}*/
}

/*
for(project in Hudson.instance.items) {
  new_builder = null
  old_builder = null
  
  if("$project.name"==build.environment.get('jobName')){
  
  for (builder in project.buildersList) {
    if (builder instanceof hudson.tasks.Shell) {
	new_command = build.environment.get('new_script')
	new_builder = new hudson.tasks.Shell(new_command)
	old_builder = builder;
	project.buildersList.add(new_builder)
	project.buildersList.remove(old_builder)
		}
	}
}
}
}else if(option == "All_Deploy_job"){
def jobPattern = "-DEPLOY"
for(project in Hudson.instance.items) {
  new_builder = null
  old_builder = null
  def matchedJobs = Jenkins.instance.items.findAll{job->
  job.name=~/$jobPattern/
  }
  for(int i=0;i<matchedJobs.size();i++){
  name = matchedJobs.getAt(i).getName()
  if("$project.name"==name){
  println "**********"
}
}
}
}else{
println "failure"
}*/









