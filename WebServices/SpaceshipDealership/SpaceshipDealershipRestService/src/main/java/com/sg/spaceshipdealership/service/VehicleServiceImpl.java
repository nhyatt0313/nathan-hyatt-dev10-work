/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.spaceshipdealership.service;

import com.sg.spaceshipdealership.dao.VehicleDao;
import com.sg.spaceshipdealership.dto.Make;
import com.sg.spaceshipdealership.dto.Model;
import com.sg.spaceshipdealership.dto.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    UserService userService;

    @Autowired
    MakeService makeService;

    @Autowired
    ModelService modelService;

    @Override
    public Vehicle create(Vehicle vehicle) throws InvalidEntryException, DoesNotExistException {
        validateRequiredFields(vehicle);
        vehicle = vehicleDao.create(vehicle);

        vehicle.setUser(userService.read(vehicle.getUser().getId()));
        vehicle.setMake(makeService.read(vehicle.getMake().getId()));
        vehicle.setModel(modelService.read(vehicle.getModel().getId()));

        return vehicle;
    }

    @Override
    public Vehicle read(Integer id) throws DoesNotExistException {
        Vehicle vehicle = vehicleDao.read(id);
        // check null
        if (vehicle == null) {
            throw new DoesNotExistException("Vehicle " + id + " Not Found");
        }
        // set user, make, and model
        vehicle.setUser(userService.read(vehicle.getUser().getId()));
        vehicle.setMake(makeService.read(vehicle.getMake().getId()));
        vehicle.setModel(modelService.read(vehicle.getModel().getId()));

        return vehicle;
    }

    @Override
    public void update(Vehicle vehicle) throws InvalidEntryException, DoesNotExistException {
        read(vehicle.getId());
        validateRequiredFields(vehicle);
        vehicleDao.update(vehicle);
    }

    @Override
    public List<Vehicle> readAll() throws DoesNotExistException {
        List<Vehicle> vehicles = vehicleDao.readAll();
        for (Vehicle v : vehicles) {
            v.setUser(userService.read(v.getUser().getId()));
            v.setMake(makeService.read(v.getMake().getId()));
            v.setModel(modelService.read(v.getModel().getId()));
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> search(String search, Boolean isNew,
            Integer minYear, Integer maxYear,
            BigDecimal minPrice, BigDecimal maxPrice) throws DoesNotExistException {

        List<Vehicle> searchedVehicles;

        if (search == null || search.trim().isEmpty()
                && minYear == null && maxYear == null
                && minPrice == null && maxPrice == null) {
            // get top 20 msrp
            if (isNew != null) {
                // filter by new/used
                searchedVehicles = vehicleDao.readAll()
                        .stream()
                        .filter(v -> v.isNewVehicle() == isNew)
                        .sorted(Comparator.comparing(v -> v.getMsrp()))
                        .collect(Collectors.toList());
                Collections.reverse(searchedVehicles);
            } else {
                // dont filter by new/used
                searchedVehicles = vehicleDao.readAll()
                        .stream()
                        .sorted(Comparator.comparing(v -> v.getMsrp()))
                        .collect(Collectors.toList());
                Collections.reverse(searchedVehicles);

            }
        } else {

            searchedVehicles = vehicleDao.search(search, isNew);

            if (minPrice != null) {
                searchedVehicles = searchedVehicles
                        .stream()
                        .filter(v -> v.getSalePrice().compareTo(minPrice) >= 0)
                        .collect(Collectors.toList());
            }
            if (maxPrice != null) {
                searchedVehicles = searchedVehicles
                        .stream()
                        .filter(v -> v.getSalePrice().compareTo(maxPrice) <= 0)
                        .collect(Collectors.toList());
            }
            if (minYear != null) {
                searchedVehicles = searchedVehicles
                        .stream()
                        .filter(v -> v.getYear() >= minYear)
                        .collect(Collectors.toList());
            }
            if (maxYear != null) {
                searchedVehicles = searchedVehicles
                        .stream()
                        .filter(v -> v.getYear() <= maxYear)
                        .collect(Collectors.toList());

            }
        }
        if (searchedVehicles.size() > 20) {
            searchedVehicles = searchedVehicles.subList(0, 19);
        }

        for (Vehicle v : searchedVehicles) {
            v.setUser(userService.read(v.getUser().getId()));
            v.setMake(makeService.read(v.getMake().getId()));
            v.setModel(modelService.read(v.getModel().getId()));
        }
        return searchedVehicles;
    }

    @Override
    public List<Vehicle> findFeatured() throws DoesNotExistException {
        List<Vehicle> featured = vehicleDao.findFeatured();
        for (Vehicle v : featured) {
            v.setUser(userService.read(v.getUser().getId()));
            v.setMake(makeService.read(v.getMake().getId()));
            v.setModel(modelService.read(v.getModel().getId()));
        }
        return featured;
    }

    private void validateRequiredFields(Vehicle vehicle) throws InvalidEntryException, DoesNotExistException {

        userService.read(vehicle.getUser().getId());
        makeService.read(vehicle.getMake().getId());
        modelService.read(vehicle.getModel().getId());

        List<String> errors = new ArrayList<>();
        // check year
        if (vehicle.getYear() == null
                || (vehicle.getYear() < 2000)
                || (vehicle.getYear() > (LocalDate.now().getYear() + 1))) {
            errors.add("Invalid Vehicle Year");
        }
        // check transmission
        if (vehicle.getTrans() == null
                || !vehicle.getTrans().equalsIgnoreCase("Automatic")
                && !vehicle.getTrans().equalsIgnoreCase("Manual")) {
            errors.add("Invalid Vehicle Transmission");
        }
        // check color (red, blue, green, silver, black)
        if (vehicle.getColor() == null
                || (!vehicle.getColor().equals("red")
                && !vehicle.getColor().equals("blue")
                && !vehicle.getColor().equals("green")
                && !vehicle.getColor().equals("silver")
                && !vehicle.getColor().equals("black"))) {
            errors.add("Invalid Vehicle Color");
        }
        // check interior (red, blue, green, silver, black)
        if (vehicle.getInterior() == null
                || (!vehicle.getInterior().equals("red")
                && !vehicle.getInterior().equals("blue")
                && !vehicle.getInterior().equals("green")
                && !vehicle.getInterior().equals("silver")
                && !vehicle.getInterior().equals("black"))) {
            errors.add("Invalid Vehicle Interior");
        }
        // check milage (non negative, less than 1000 for new vehicles, used must be 1000+)
        if (vehicle.getMileage() == null
                || (vehicle.getMileage() < 0)
                || (vehicle.isNewVehicle() && (vehicle.getMileage() > 1000)) // new vehicle cant have more than 1000 miles
                || (!vehicle.isNewVehicle() && (vehicle.getMileage() < 1000))) { // used vehicles must have more than 1000 miles
            errors.add("Invalid Vehicle Mileage");
        }
        // check vin (not null)
        if (vehicle.getVin() == null || vehicle.getVin().trim().isEmpty()) {
            errors.add("Invalid Vehicle Vin");
        }
        // check MSRP (non negative)
        if (vehicle.getMsrp() == null
                || vehicle.getMsrp().compareTo(BigDecimal.ZERO) == -1) {
            errors.add("Invalid Vehicle Msrp");
        }
        // check sale price
        if (vehicle.getMsrp() == null
                || vehicle.getSalePrice() == null
                || vehicle.getSalePrice().compareTo(vehicle.getMsrp()) > 0) {
            errors.add("Invalid Vehicle Sale Price");
        }
        // check description
        if (vehicle.getDescription() == null
                || vehicle.getDescription().trim().isEmpty()) {
            errors.add("Invalid Vehicle Description");
        }
        // check images
        if (vehicle.getFileImg() == null
                || (!vehicle.getFileImg().endsWith(".png")
                && !vehicle.getFileImg().endsWith(".jpg")
                && !vehicle.getFileImg().endsWith(".jpeg"))) {
            errors.add("Invalid Vehicle Image Type");
        }

        // throw the errors if there are any
        String allErrors = "";
        int numErrors = errors.size();
        int iter = 0;
        if (numErrors != 0) {
            for (String s : errors) {
                iter++;
                if (iter < numErrors) {
                    allErrors += (s + ", ");
                } else {
                    allErrors += s;
                }
            }
            // could possibly pass the list to exception class instead of the formatted string
            throw new InvalidEntryException(allErrors);
        }
    }

    @Override
    public List<InventoryReportItem> getInventoryReport(boolean newVehicle) throws DoesNotExistException {

        List<Vehicle> vehicles = vehicleDao.search("",newVehicle);
        //List<Vehicle> vehicles = vehicleDao.readAllSorted(newVehicle);

        for (Vehicle v : vehicles) {
            v.setUser(userService.read(v.getUser().getId()));
            v.setMake(makeService.read(v.getMake().getId()));
            v.setModel(modelService.read(v.getModel().getId()));
        }

        List<InventoryReportItem> items = new ArrayList<>();

        for (Vehicle v : vehicles) {
            InventoryReportItem item = new InventoryReportItem(v);
            items.add(item);
        }

        List<InventoryReportItem> combinedItems = new ArrayList<>();

        for (InventoryReportItem item : items) {
            if (!combinedItems.isEmpty()) {
                // conbinedItems is not empty
                if (combinedItems.contains(item)) {
                    // item already exsist in combinedItems
                    
                    // add to count
                    combinedItems.get(combinedItems.indexOf(item)).count();
                    // add items msrp to item in combinedItems
                    combinedItems.get(combinedItems.indexOf(item)).addToStockVal(item.getStockValue());
                } else {
                    // item does not exist in conbinedItems - add it
                    combinedItems.add(item);
                }

            } else {
                // combinedItems is empty - add the first item
                combinedItems.add(item);
            }

        }

        return combinedItems;
    }

    public class InventoryReportItem {

        private final Integer year;
        private final String make;
        private final String model;
        private Integer count;
        private BigDecimal stockValue;

        public InventoryReportItem(Vehicle v) {
            this.year = v.getYear();
            this.make = v.getMake().getName();
            this.model = v.getModel().getName();
            
            this.count = 1;
            this.stockValue = v.getMsrp();
        }
        
        public void count(){
            this.count++;
        }
        
        public void addToStockVal(BigDecimal addMsrp){
            this.stockValue = this.stockValue.add(addMsrp);
        }

        public Integer getYear() {
            return year;
        }

        public String getMake() {
            return make;
        }

        public String getModel() {
            return model;
        }

        public Integer getCount() {
            return count;
        }

        public BigDecimal getStockValue() {
            return stockValue;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 73 * hash + Objects.hashCode(this.year);
            hash = 73 * hash + Objects.hashCode(this.make);
            hash = 73 * hash + Objects.hashCode(this.model);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final InventoryReportItem other = (InventoryReportItem) obj;
            if (!Objects.equals(this.make, other.make)) {
                return false;
            }
            if (!Objects.equals(this.model, other.model)) {
                return false;
            }
            if (!Objects.equals(this.year, other.year)) {
                return false;
            }
            return true;
        }  

    }

}
