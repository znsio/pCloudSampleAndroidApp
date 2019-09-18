require 'net/http'
require 'openssl'
require 'json'

# Read Command Line Arrguments   
# i.e ruby dtb.rb --email=bikee.bihari@sstsinc.com --api=stcr6bcrcx9c6ssp5mp2bdtw --did=571 --appname=old_school.android.debug__21_.apk --duration=5

args = {}
ARGV.each do |arg|
  match = /--(?<key>.*?)=(?<value>.*)/.match(arg)
  args[match[:key]] = match[:value] # e.g. args['first_name'] = 'donald'
end

#puts args['email'] + ' ' + args['api'] + ' ' + args['did'] + ' ' + args['appname']

api = args['api'] 
email = args['email'] 
did = args['did']
appname = args['appname']
duration = args['duration']


# Method for authentication from user by providing email_id and api (can get it from setting page) 

def authenticate(email, api)
uri = URI('https://device.pcloudy.com/api/access')

Net::HTTP.start(uri.host, uri.port,
  :use_ssl => uri.scheme == 'https', 
  :verify_mode => OpenSSL::SSL::VERIFY_NONE) do |http|
  request = Net::HTTP::Get.new uri.request_uri
  request.basic_auth email,api
  response = http.request request # Net::HTTPResponse object
  #puts response
  #puts response.body
  authresponce = JSON.parse(response.body)
  token = authresponce["result"]["token"]
  puts token
  return token
end 
end


# Method for booking of device need token(Authentication Method will return), duration in minitues and divice id

def bookdevice(token, duration = 5 , id)

uri = URI.parse("https://device.pcloudy.com/api/book_device")
@toSend = {
   "token" => token ,"duration"=>duration, "id"=>id
}.to_json

https = Net::HTTP.new(uri.host,uri.port)
https.use_ssl = true

req = Net::HTTP::Post.new(uri.path, initheader = {'Content-Type' =>'application/json'})

req.body = "#{@toSend}"
res = https.request(req)
puts "Response #{res.code} #{res.message}: #{res.body}"
bookingdetails = JSON.parse(res.body)

rid = bookingdetails["result"]["rid"]
return rid
end

# Method for execute any any adb command require token (from Authentication method)  rid (Reservation id frome bookdevice)
# and command which need to execute on device(Limited command not all adb command are allowed) 

def executeadb(token, rid , adbcommand)
uri = URI.parse("https://device.pcloudy.com/api/execute_adb")
@toSend = {
   "token" => token , "rid" => rid, "adbCommand" => adbcommand
}.to_json

https = Net::HTTP.new(uri.host,uri.port)
https.use_ssl = true

req = Net::HTTP::Post.new(uri.path, initheader = {'Content-Type' =>'application/json'})

req.body = "#{@toSend}"
res = https.request(req)
puts "Response #{res.code} #{res.message}: #{res.body}"
adbresp = JSON.parse(res.body)
output = adbresp["result"]["adbreply"]
puts output
return output
end	



# Method for installation of applicatoin in booked device token (from Authentication method)  rid(Reservation id from bookdevice)
# and filename( file name must present in my app data) and Permission grant(true or false) 


def installandlaunch(token, rid, filename , grant_all_permissions )
uri = URI.parse("https://device.pcloudy.com/api/install_app")
@toSend = {
   "token"=>token, "rid"=>rid, "filename"=>filename, "grant_all_permissions"=>grant_all_permissions
}.to_json

https = Net::HTTP.new(uri.host,uri.port)
https.use_ssl = true
req = Net::HTTP::Post.new(uri.path, initheader = {'Content-Type' =>'application/json'})
req.body = "#{@toSend}"
res = https.request(req)
puts "Response #{res.code} #{res.message}: #{res.body}"
installresp = JSON.parse(res.body)
output = installresp["result"]["msg"]
puts output
end

#Authenticating User

token = authenticate(email,api)  

# Booking Device 
rid = bookdevice(token, duration , did)

#Executing Adb Commands
msg = executeadb(token , rid ,"adb shell getprop | grep ro.build.version.release")

sleep(45)

#Installation of apk 
output = installandlaunch(token, rid, appname , true)

puts output

#Executing Adb Commands
msg = executeadb(token , rid ,"adb shell getprop | grep ro.build.version.release")

puts msg
