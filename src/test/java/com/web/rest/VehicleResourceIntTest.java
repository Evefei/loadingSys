package com.web.rest;

import com.LoadingSysApp;

import com.domain.Vehicle;
import com.repository.VehicleRepository;
import com.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;


import static com.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VehicleResource REST controller.
 *
 * @see VehicleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoadingSysApp.class)
public class VehicleResourceIntTest {

    private static final String DEFAULT_CAR_LICENSE_NO = "AAAAAAAAAA";
    private static final String UPDATED_CAR_LICENSE_NO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_LENGTH = new BigDecimal(1);
    private static final BigDecimal UPDATED_LENGTH = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WIDTH = new BigDecimal(1);
    private static final BigDecimal UPDATED_WIDTH = new BigDecimal(2);

    private static final BigDecimal DEFAULT_HEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_HEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VEHICLE_VOLUME = new BigDecimal(1);
    private static final BigDecimal UPDATED_VEHICLE_VOLUME = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VEHICLE_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_VEHICLE_WEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_UNDERPAN_HEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNDERPAN_HEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CRANK_HEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CRANK_HEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CRANK_WIDTH = new BigDecimal(1);
    private static final BigDecimal UPDATED_CRANK_WIDTH = new BigDecimal(2);

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVehicleMockMvc;

    private Vehicle vehicle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VehicleResource vehicleResource = new VehicleResource(vehicleRepository);
        this.restVehicleMockMvc = MockMvcBuilders.standaloneSetup(vehicleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vehicle createEntity(EntityManager em) {
        Vehicle vehicle = new Vehicle()
            .carLicenseNo(DEFAULT_CAR_LICENSE_NO)
            .length(DEFAULT_LENGTH)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT)
            .vehicleVolume(DEFAULT_VEHICLE_VOLUME)
            .vehicleWeight(DEFAULT_VEHICLE_WEIGHT)
            .underpanHeight(DEFAULT_UNDERPAN_HEIGHT)
            .crankHeight(DEFAULT_CRANK_HEIGHT)
            .crankWidth(DEFAULT_CRANK_WIDTH);
        return vehicle;
    }

    @Before
    public void initTest() {
        vehicle = createEntity(em);
    }

    @Test
    @Transactional
    public void createVehicle() throws Exception {
        int databaseSizeBeforeCreate = vehicleRepository.findAll().size();

        // Create the Vehicle
        restVehicleMockMvc.perform(post("/api/vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicle)))
            .andExpect(status().isCreated());

        // Validate the Vehicle in the database
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeCreate + 1);
        Vehicle testVehicle = vehicleList.get(vehicleList.size() - 1);
        assertThat(testVehicle.getCarLicenseNo()).isEqualTo(DEFAULT_CAR_LICENSE_NO);
        assertThat(testVehicle.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testVehicle.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testVehicle.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testVehicle.getVehicleVolume()).isEqualTo(DEFAULT_VEHICLE_VOLUME);
        assertThat(testVehicle.getVehicleWeight()).isEqualTo(DEFAULT_VEHICLE_WEIGHT);
        assertThat(testVehicle.getUnderpanHeight()).isEqualTo(DEFAULT_UNDERPAN_HEIGHT);
        assertThat(testVehicle.getCrankHeight()).isEqualTo(DEFAULT_CRANK_HEIGHT);
        assertThat(testVehicle.getCrankWidth()).isEqualTo(DEFAULT_CRANK_WIDTH);
    }

    @Test
    @Transactional
    public void createVehicleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vehicleRepository.findAll().size();

        // Create the Vehicle with an existing ID
        vehicle.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVehicleMockMvc.perform(post("/api/vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicle)))
            .andExpect(status().isBadRequest());

        // Validate the Vehicle in the database
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVehicles() throws Exception {
        // Initialize the database
        vehicleRepository.saveAndFlush(vehicle);

        // Get all the vehicleList
        restVehicleMockMvc.perform(get("/api/vehicles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehicle.getId().intValue())))
            .andExpect(jsonPath("$.[*].carLicenseNo").value(hasItem(DEFAULT_CAR_LICENSE_NO.toString())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH.intValue())))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH.intValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].vehicleVolume").value(hasItem(DEFAULT_VEHICLE_VOLUME.intValue())))
            .andExpect(jsonPath("$.[*].vehicleWeight").value(hasItem(DEFAULT_VEHICLE_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].underpanHeight").value(hasItem(DEFAULT_UNDERPAN_HEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].crankHeight").value(hasItem(DEFAULT_CRANK_HEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].crankWidth").value(hasItem(DEFAULT_CRANK_WIDTH.intValue())));
    }
    
    @Test
    @Transactional
    public void getVehicle() throws Exception {
        // Initialize the database
        vehicleRepository.saveAndFlush(vehicle);

        // Get the vehicle
        restVehicleMockMvc.perform(get("/api/vehicles/{id}", vehicle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vehicle.getId().intValue()))
            .andExpect(jsonPath("$.carLicenseNo").value(DEFAULT_CAR_LICENSE_NO.toString()))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH.intValue()))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH.intValue()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.intValue()))
            .andExpect(jsonPath("$.vehicleVolume").value(DEFAULT_VEHICLE_VOLUME.intValue()))
            .andExpect(jsonPath("$.vehicleWeight").value(DEFAULT_VEHICLE_WEIGHT.intValue()))
            .andExpect(jsonPath("$.underpanHeight").value(DEFAULT_UNDERPAN_HEIGHT.intValue()))
            .andExpect(jsonPath("$.crankHeight").value(DEFAULT_CRANK_HEIGHT.intValue()))
            .andExpect(jsonPath("$.crankWidth").value(DEFAULT_CRANK_WIDTH.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingVehicle() throws Exception {
        // Get the vehicle
        restVehicleMockMvc.perform(get("/api/vehicles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVehicle() throws Exception {
        // Initialize the database
        vehicleRepository.saveAndFlush(vehicle);

        int databaseSizeBeforeUpdate = vehicleRepository.findAll().size();

        // Update the vehicle
        Vehicle updatedVehicle = vehicleRepository.findById(vehicle.getId()).get();
        // Disconnect from session so that the updates on updatedVehicle are not directly saved in db
        em.detach(updatedVehicle);
        updatedVehicle
            .carLicenseNo(UPDATED_CAR_LICENSE_NO)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .vehicleVolume(UPDATED_VEHICLE_VOLUME)
            .vehicleWeight(UPDATED_VEHICLE_WEIGHT)
            .underpanHeight(UPDATED_UNDERPAN_HEIGHT)
            .crankHeight(UPDATED_CRANK_HEIGHT)
            .crankWidth(UPDATED_CRANK_WIDTH);

        restVehicleMockMvc.perform(put("/api/vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVehicle)))
            .andExpect(status().isOk());

        // Validate the Vehicle in the database
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeUpdate);
        Vehicle testVehicle = vehicleList.get(vehicleList.size() - 1);
        assertThat(testVehicle.getCarLicenseNo()).isEqualTo(UPDATED_CAR_LICENSE_NO);
        assertThat(testVehicle.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testVehicle.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testVehicle.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testVehicle.getVehicleVolume()).isEqualTo(UPDATED_VEHICLE_VOLUME);
        assertThat(testVehicle.getVehicleWeight()).isEqualTo(UPDATED_VEHICLE_WEIGHT);
        assertThat(testVehicle.getUnderpanHeight()).isEqualTo(UPDATED_UNDERPAN_HEIGHT);
        assertThat(testVehicle.getCrankHeight()).isEqualTo(UPDATED_CRANK_HEIGHT);
        assertThat(testVehicle.getCrankWidth()).isEqualTo(UPDATED_CRANK_WIDTH);
    }

    @Test
    @Transactional
    public void updateNonExistingVehicle() throws Exception {
        int databaseSizeBeforeUpdate = vehicleRepository.findAll().size();

        // Create the Vehicle

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehicleMockMvc.perform(put("/api/vehicles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicle)))
            .andExpect(status().isBadRequest());

        // Validate the Vehicle in the database
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVehicle() throws Exception {
        // Initialize the database
        vehicleRepository.saveAndFlush(vehicle);

        int databaseSizeBeforeDelete = vehicleRepository.findAll().size();

        // Get the vehicle
        restVehicleMockMvc.perform(delete("/api/vehicles/{id}", vehicle.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        assertThat(vehicleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vehicle.class);
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(1L);
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setId(vehicle1.getId());
        assertThat(vehicle1).isEqualTo(vehicle2);
        vehicle2.setId(2L);
        assertThat(vehicle1).isNotEqualTo(vehicle2);
        vehicle1.setId(null);
        assertThat(vehicle1).isNotEqualTo(vehicle2);
    }
}
