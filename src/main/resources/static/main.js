var app = angular.module("TouristManagement", []);

// Controller Part
app.controller("TouristController", function($scope, $http) {


    $scope.tourists = [];
    $scope.tourist = {
        id: 1,
        name: "",
        surname: "",
        gender: "",
        country: "",
        remarks: "",
        dateOfBirth: ""
    };

    // Now load the data from server
    _refreshTouristData();

    var editMode = false;

    // HTTP POST/PUT methods for add/edit tourists
    // Call: http://localhost:8080/tourist
    $scope.submitTourist = function() {

        var method = "POST";
        var url = 'tourist/tourist';

        if (editMode)
        {
        method = "PUT";
        editMode = false;
        }

console.log(url);
console.log(method);
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.tourist),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    $scope.createTourist = function() {
        _clearFormData();
    }

    // HTTP DELETE- delete Tourist by Id
    // Call: http://localhost:8080/tourist/{id}
    $scope.deleteTourist = function(tourist) {
        $http({
            method: 'DELETE',
            url: 'tourist/tourist/' + tourist.id
        }).then(_success, _error);
    };

    // In case of edit
    $scope.editTourist = function(tourist) {
        editMode = true;
        $scope.tourist.id = tourist.id;
        $scope.tourist.name = tourist.name;
    };

    // Private Method
    // HTTP GET- get all employees collection
    // Call: http://localhost:8080/tourists
    function _refreshTouristData() {
        $http({
            method: 'GET',
            url: 'tourist/tourists'
        }).then(
            function(res) { // success
                $scope.tourists = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _success(res) {
        _refreshTouristData();
        _clearFormData();
    }

    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }

    // Clear the form
    function _clearFormData() {
        $scope.tourist.id = -1;
        $scope.tourist.name = "";
        $scope.tourist.surname = "";
        $scope.tourist.gender = "";
        $scope.tourist.country = "";
        $scope.tourist.remarks = "";
        $scope.tourist.dateOfBirth = "";
    };









    $scope.flights = [];
        $scope.flight = {
            id: 1,
            name: "",
            departure: "",
            arrive: "",
            seat: "",
            price: ""
        };

        // Now load the data from server
        _refreshFlightData();

        var editMode = false;

        // HTTP POST/PUT methods for add/edit flights
        // Call: http://localhost:8080/tourist
        $scope.submitFlight = function() {

            var method = "POST";
            var url = 'flight/flight';

            if (editMode)
            {
            method = "PUT";
            editMode = false;
            }

console.log(url);
console.log(method);
            $http({
                method: method,
                url: url,
                data: angular.toJson($scope.flight),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(_success, _error);
        };

        $scope.createFlight = function() {
            _clearFormData();
        }

        // HTTP DELETE- delete Tourist by Id
        // Call: http://localhost:8080/tourist/{id}
        $scope.deleteFlight = function(flight) {
            $http({
                method: 'DELETE',
                url: 'flight/flight/' + flight.id
            }).then(_success, _error);
        };

        // In case of edit
        $scope.editFlight = function(flight) {
            editMode = true;
            $scope.flight.id = flight.id;
            $scope.flight.name = flight.name;
        };

        // Private Method
        // HTTP GET- get all employees collection
        // Call: http://localhost:8080/flight
        function _refreshFlightData() {
            $http({
                method: 'GET',
                url: 'flight/flights'
            }).then(
                function(res) { // success
                    $scope.flights = res.data;
                },
                function(res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }

        function _success(res) {
            _refreshFlightData();
            _clearFormData();
        }

        function _error(res) {
            var data = res.data;
            var status = res.status;
            var header = res.header;
            var config = res.config;
            alert("Error: " + status + ":" + data);
        }

        // Clear the form
        function _clearFormData() {
            $scope.flight.id = -1;
            $scope.flight.name = "";
            $scope.flight.surname = "";
            $scope.flight.gender = "";
            $scope.flight.country = "";
            $scope.flight.remarks = "";
            $scope.flight.dateOfBirth = "";
        };
});