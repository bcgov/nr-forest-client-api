/*
 * Forest Client Generator
 *
 * Generates a test forest client
 *
 * Contact: c.gustafson@cgi.com
 */
package forestclient

import (
	"math/rand"
)

func CreateRandomForestClient() ForestClient {

	return ForestClient{
		Id:         randomForestClientID(),
		ClientName: randomForestClientName(),
		IsActive:   randomForestClientStatus(),
		TypeCode:   randomForestClientType(),
	}

}

func CreateRandomForestClientList() []ForestClient {
	var len = 1000
	var list = make([]ForestClient, len)

	for i := range list {
		list[i] = CreateRandomForestClient()
	}
	return list
}

func randomForestClientID() string {
	return randomString(8, "0123456789")
}

func randomForestClientName() string {
	return randomString(20, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ")
}

func randomForestClientStatus() bool {
	return rand.Intn(2) == 1
}

func randomForestClientType() string {
	return randomString(1, "ABCFGILPRSTU")
}

func randomString(n int, letters string) string {
	var options = []rune(letters)

	s := make([]rune, n)
	for i := range s {
		s[i] = options[rand.Intn(len(options))]
	}
	return string(s)
}
