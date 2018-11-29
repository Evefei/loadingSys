package com.web.rest;

import com.LoadingSysApp;

import com.domain.Loading;
import com.repository.LoadingRepository;
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
 * Test class for the LoadingResource REST controller.
 *
 * @see LoadingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoadingSysApp.class)
public class LoadingResourceIntTest {

    private static final String DEFAULT_LOADING_NO = "AAAAAAAAAA";
    private static final String UPDATED_LOADING_NO = "BBBBBBBBBB";

    private static final Integer DEFAULT_VOLUME_UTILIZATION = 1;
    private static final Integer UPDATED_VOLUME_UTILIZATION = 2;

    private static final Integer DEFAULT_WEIGHT_UTILIZATION = 1;
    private static final Integer UPDATED_WEIGHT_UTILIZATION = 2;

    private static final Integer DEFAULT_MATERIAL_QUANTITY = 1;
    private static final Integer UPDATED_MATERIAL_QUANTITY = 2;

    private static final BigDecimal DEFAULT_MATERIAL_VOLUME = new BigDecimal(1);
    private static final BigDecimal UPDATED_MATERIAL_VOLUME = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MATERIAL_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MATERIAL_WEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LENGTH_REMAIN = new BigDecimal(1);
    private static final BigDecimal UPDATED_LENGTH_REMAIN = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WIDTH_REMAIN = new BigDecimal(1);
    private static final BigDecimal UPDATED_WIDTH_REMAIN = new BigDecimal(2);

    private static final BigDecimal DEFAULT_HEIGHT_REMAIN = new BigDecimal(1);
    private static final BigDecimal UPDATED_HEIGHT_REMAIN = new BigDecimal(2);

    @Autowired
    private LoadingRepository loadingRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLoadingMockMvc;

    private Loading loading;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LoadingResource loadingResource = new LoadingResource(loadingRepository);
        this.restLoadingMockMvc = MockMvcBuilders.standaloneSetup(loadingResource)
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
    public static Loading createEntity(EntityManager em) {
        Loading loading = new Loading()
            .loadingNo(DEFAULT_LOADING_NO)
            .volumeUtilization(DEFAULT_VOLUME_UTILIZATION)
            .weightUtilization(DEFAULT_WEIGHT_UTILIZATION)
            .materialQuantity(DEFAULT_MATERIAL_QUANTITY)
            .materialVolume(DEFAULT_MATERIAL_VOLUME)
            .materialWeight(DEFAULT_MATERIAL_WEIGHT)
            .lengthRemain(DEFAULT_LENGTH_REMAIN)
            .widthRemain(DEFAULT_WIDTH_REMAIN)
            .heightRemain(DEFAULT_HEIGHT_REMAIN);
        return loading;
    }

    @Before
    public void initTest() {
        loading = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoading() throws Exception {
        int databaseSizeBeforeCreate = loadingRepository.findAll().size();

        // Create the Loading
        restLoadingMockMvc.perform(post("/api/loadings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loading)))
            .andExpect(status().isCreated());

        // Validate the Loading in the database
        List<Loading> loadingList = loadingRepository.findAll();
        assertThat(loadingList).hasSize(databaseSizeBeforeCreate + 1);
        Loading testLoading = loadingList.get(loadingList.size() - 1);
        assertThat(testLoading.getLoadingNo()).isEqualTo(DEFAULT_LOADING_NO);
        assertThat(testLoading.getVolumeUtilization()).isEqualTo(DEFAULT_VOLUME_UTILIZATION);
        assertThat(testLoading.getWeightUtilization()).isEqualTo(DEFAULT_WEIGHT_UTILIZATION);
        assertThat(testLoading.getMaterialQuantity()).isEqualTo(DEFAULT_MATERIAL_QUANTITY);
        assertThat(testLoading.getMaterialVolume()).isEqualTo(DEFAULT_MATERIAL_VOLUME);
        assertThat(testLoading.getMaterialWeight()).isEqualTo(DEFAULT_MATERIAL_WEIGHT);
        assertThat(testLoading.getLengthRemain()).isEqualTo(DEFAULT_LENGTH_REMAIN);
        assertThat(testLoading.getWidthRemain()).isEqualTo(DEFAULT_WIDTH_REMAIN);
        assertThat(testLoading.getHeightRemain()).isEqualTo(DEFAULT_HEIGHT_REMAIN);
    }

    @Test
    @Transactional
    public void createLoadingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loadingRepository.findAll().size();

        // Create the Loading with an existing ID
        loading.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoadingMockMvc.perform(post("/api/loadings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loading)))
            .andExpect(status().isBadRequest());

        // Validate the Loading in the database
        List<Loading> loadingList = loadingRepository.findAll();
        assertThat(loadingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLoadings() throws Exception {
        // Initialize the database
        loadingRepository.saveAndFlush(loading);

        // Get all the loadingList
        restLoadingMockMvc.perform(get("/api/loadings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loading.getId().intValue())))
            .andExpect(jsonPath("$.[*].loadingNo").value(hasItem(DEFAULT_LOADING_NO.toString())))
            .andExpect(jsonPath("$.[*].volumeUtilization").value(hasItem(DEFAULT_VOLUME_UTILIZATION)))
            .andExpect(jsonPath("$.[*].weightUtilization").value(hasItem(DEFAULT_WEIGHT_UTILIZATION)))
            .andExpect(jsonPath("$.[*].materialQuantity").value(hasItem(DEFAULT_MATERIAL_QUANTITY)))
            .andExpect(jsonPath("$.[*].materialVolume").value(hasItem(DEFAULT_MATERIAL_VOLUME.intValue())))
            .andExpect(jsonPath("$.[*].materialWeight").value(hasItem(DEFAULT_MATERIAL_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].lengthRemain").value(hasItem(DEFAULT_LENGTH_REMAIN.intValue())))
            .andExpect(jsonPath("$.[*].widthRemain").value(hasItem(DEFAULT_WIDTH_REMAIN.intValue())))
            .andExpect(jsonPath("$.[*].heightRemain").value(hasItem(DEFAULT_HEIGHT_REMAIN.intValue())));
    }
    
    @Test
    @Transactional
    public void getLoading() throws Exception {
        // Initialize the database
        loadingRepository.saveAndFlush(loading);

        // Get the loading
        restLoadingMockMvc.perform(get("/api/loadings/{id}", loading.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loading.getId().intValue()))
            .andExpect(jsonPath("$.loadingNo").value(DEFAULT_LOADING_NO.toString()))
            .andExpect(jsonPath("$.volumeUtilization").value(DEFAULT_VOLUME_UTILIZATION))
            .andExpect(jsonPath("$.weightUtilization").value(DEFAULT_WEIGHT_UTILIZATION))
            .andExpect(jsonPath("$.materialQuantity").value(DEFAULT_MATERIAL_QUANTITY))
            .andExpect(jsonPath("$.materialVolume").value(DEFAULT_MATERIAL_VOLUME.intValue()))
            .andExpect(jsonPath("$.materialWeight").value(DEFAULT_MATERIAL_WEIGHT.intValue()))
            .andExpect(jsonPath("$.lengthRemain").value(DEFAULT_LENGTH_REMAIN.intValue()))
            .andExpect(jsonPath("$.widthRemain").value(DEFAULT_WIDTH_REMAIN.intValue()))
            .andExpect(jsonPath("$.heightRemain").value(DEFAULT_HEIGHT_REMAIN.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLoading() throws Exception {
        // Get the loading
        restLoadingMockMvc.perform(get("/api/loadings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoading() throws Exception {
        // Initialize the database
        loadingRepository.saveAndFlush(loading);

        int databaseSizeBeforeUpdate = loadingRepository.findAll().size();

        // Update the loading
        Loading updatedLoading = loadingRepository.findById(loading.getId()).get();
        // Disconnect from session so that the updates on updatedLoading are not directly saved in db
        em.detach(updatedLoading);
        updatedLoading
            .loadingNo(UPDATED_LOADING_NO)
            .volumeUtilization(UPDATED_VOLUME_UTILIZATION)
            .weightUtilization(UPDATED_WEIGHT_UTILIZATION)
            .materialQuantity(UPDATED_MATERIAL_QUANTITY)
            .materialVolume(UPDATED_MATERIAL_VOLUME)
            .materialWeight(UPDATED_MATERIAL_WEIGHT)
            .lengthRemain(UPDATED_LENGTH_REMAIN)
            .widthRemain(UPDATED_WIDTH_REMAIN)
            .heightRemain(UPDATED_HEIGHT_REMAIN);

        restLoadingMockMvc.perform(put("/api/loadings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLoading)))
            .andExpect(status().isOk());

        // Validate the Loading in the database
        List<Loading> loadingList = loadingRepository.findAll();
        assertThat(loadingList).hasSize(databaseSizeBeforeUpdate);
        Loading testLoading = loadingList.get(loadingList.size() - 1);
        assertThat(testLoading.getLoadingNo()).isEqualTo(UPDATED_LOADING_NO);
        assertThat(testLoading.getVolumeUtilization()).isEqualTo(UPDATED_VOLUME_UTILIZATION);
        assertThat(testLoading.getWeightUtilization()).isEqualTo(UPDATED_WEIGHT_UTILIZATION);
        assertThat(testLoading.getMaterialQuantity()).isEqualTo(UPDATED_MATERIAL_QUANTITY);
        assertThat(testLoading.getMaterialVolume()).isEqualTo(UPDATED_MATERIAL_VOLUME);
        assertThat(testLoading.getMaterialWeight()).isEqualTo(UPDATED_MATERIAL_WEIGHT);
        assertThat(testLoading.getLengthRemain()).isEqualTo(UPDATED_LENGTH_REMAIN);
        assertThat(testLoading.getWidthRemain()).isEqualTo(UPDATED_WIDTH_REMAIN);
        assertThat(testLoading.getHeightRemain()).isEqualTo(UPDATED_HEIGHT_REMAIN);
    }

    @Test
    @Transactional
    public void updateNonExistingLoading() throws Exception {
        int databaseSizeBeforeUpdate = loadingRepository.findAll().size();

        // Create the Loading

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoadingMockMvc.perform(put("/api/loadings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loading)))
            .andExpect(status().isBadRequest());

        // Validate the Loading in the database
        List<Loading> loadingList = loadingRepository.findAll();
        assertThat(loadingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLoading() throws Exception {
        // Initialize the database
        loadingRepository.saveAndFlush(loading);

        int databaseSizeBeforeDelete = loadingRepository.findAll().size();

        // Get the loading
        restLoadingMockMvc.perform(delete("/api/loadings/{id}", loading.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Loading> loadingList = loadingRepository.findAll();
        assertThat(loadingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Loading.class);
        Loading loading1 = new Loading();
        loading1.setId(1L);
        Loading loading2 = new Loading();
        loading2.setId(loading1.getId());
        assertThat(loading1).isEqualTo(loading2);
        loading2.setId(2L);
        assertThat(loading1).isNotEqualTo(loading2);
        loading1.setId(null);
        assertThat(loading1).isNotEqualTo(loading2);
    }
}
