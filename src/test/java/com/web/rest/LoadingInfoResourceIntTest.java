package com.web.rest;

import com.LoadingSysApp;

import com.domain.LoadingInfo;
import com.repository.LoadingInfoRepository;
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
 * Test class for the LoadingInfoResource REST controller.
 *
 * @see LoadingInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoadingSysApp.class)
public class LoadingInfoResourceIntTest {

    private static final Integer DEFAULT_STEP = 1;
    private static final Integer UPDATED_STEP = 2;

    private static final String DEFAULT_MATERIAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MATERIAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COLOR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LAYOUT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_LAYOUT_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final Integer DEFAULT_ROW = 1;
    private static final Integer UPDATED_ROW = 2;

    private static final Integer DEFAULT_COLUMN = 1;
    private static final Integer UPDATED_COLUMN = 2;

    private static final Integer DEFAULT_LAYER = 1;
    private static final Integer UPDATED_LAYER = 2;

    private static final BigDecimal DEFAULT_LENGTH_POSITION = new BigDecimal(1);
    private static final BigDecimal UPDATED_LENGTH_POSITION = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WIDTH_POSITION = new BigDecimal(1);
    private static final BigDecimal UPDATED_WIDTH_POSITION = new BigDecimal(2);

    private static final BigDecimal DEFAULT_HEIGHT_POSITION = new BigDecimal(1);
    private static final BigDecimal UPDATED_HEIGHT_POSITION = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LENGTH = new BigDecimal(1);
    private static final BigDecimal UPDATED_LENGTH = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WIDTH = new BigDecimal(1);
    private static final BigDecimal UPDATED_WIDTH = new BigDecimal(2);

    private static final BigDecimal DEFAULT_HEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_HEIGHT = new BigDecimal(2);

    @Autowired
    private LoadingInfoRepository loadingInfoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLoadingInfoMockMvc;

    private LoadingInfo loadingInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LoadingInfoResource loadingInfoResource = new LoadingInfoResource(loadingInfoRepository);
        this.restLoadingInfoMockMvc = MockMvcBuilders.standaloneSetup(loadingInfoResource)
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
    public static LoadingInfo createEntity(EntityManager em) {
        LoadingInfo loadingInfo = new LoadingInfo()
            .step(DEFAULT_STEP)
            .materialCode(DEFAULT_MATERIAL_CODE)
            .colorCode(DEFAULT_COLOR_CODE)
            .layoutType(DEFAULT_LAYOUT_TYPE)
            .quantity(DEFAULT_QUANTITY)
            .row(DEFAULT_ROW)
            .column(DEFAULT_COLUMN)
            .layer(DEFAULT_LAYER)
            .lengthPosition(DEFAULT_LENGTH_POSITION)
            .widthPosition(DEFAULT_WIDTH_POSITION)
            .heightPosition(DEFAULT_HEIGHT_POSITION)
            .length(DEFAULT_LENGTH)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT);
        return loadingInfo;
    }

    @Before
    public void initTest() {
        loadingInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoadingInfo() throws Exception {
        int databaseSizeBeforeCreate = loadingInfoRepository.findAll().size();

        // Create the LoadingInfo
        restLoadingInfoMockMvc.perform(post("/api/loading-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loadingInfo)))
            .andExpect(status().isCreated());

        // Validate the LoadingInfo in the database
        List<LoadingInfo> loadingInfoList = loadingInfoRepository.findAll();
        assertThat(loadingInfoList).hasSize(databaseSizeBeforeCreate + 1);
        LoadingInfo testLoadingInfo = loadingInfoList.get(loadingInfoList.size() - 1);
        assertThat(testLoadingInfo.getStep()).isEqualTo(DEFAULT_STEP);
        assertThat(testLoadingInfo.getMaterialCode()).isEqualTo(DEFAULT_MATERIAL_CODE);
        assertThat(testLoadingInfo.getColorCode()).isEqualTo(DEFAULT_COLOR_CODE);
        assertThat(testLoadingInfo.getLayoutType()).isEqualTo(DEFAULT_LAYOUT_TYPE);
        assertThat(testLoadingInfo.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testLoadingInfo.getRow()).isEqualTo(DEFAULT_ROW);
        assertThat(testLoadingInfo.getColumn()).isEqualTo(DEFAULT_COLUMN);
        assertThat(testLoadingInfo.getLayer()).isEqualTo(DEFAULT_LAYER);
        assertThat(testLoadingInfo.getLengthPosition()).isEqualTo(DEFAULT_LENGTH_POSITION);
        assertThat(testLoadingInfo.getWidthPosition()).isEqualTo(DEFAULT_WIDTH_POSITION);
        assertThat(testLoadingInfo.getHeightPosition()).isEqualTo(DEFAULT_HEIGHT_POSITION);
        assertThat(testLoadingInfo.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testLoadingInfo.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testLoadingInfo.getHeight()).isEqualTo(DEFAULT_HEIGHT);
    }

    @Test
    @Transactional
    public void createLoadingInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loadingInfoRepository.findAll().size();

        // Create the LoadingInfo with an existing ID
        loadingInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoadingInfoMockMvc.perform(post("/api/loading-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loadingInfo)))
            .andExpect(status().isBadRequest());

        // Validate the LoadingInfo in the database
        List<LoadingInfo> loadingInfoList = loadingInfoRepository.findAll();
        assertThat(loadingInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLoadingInfos() throws Exception {
        // Initialize the database
        loadingInfoRepository.saveAndFlush(loadingInfo);

        // Get all the loadingInfoList
        restLoadingInfoMockMvc.perform(get("/api/loading-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loadingInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].step").value(hasItem(DEFAULT_STEP)))
            .andExpect(jsonPath("$.[*].materialCode").value(hasItem(DEFAULT_MATERIAL_CODE.toString())))
            .andExpect(jsonPath("$.[*].colorCode").value(hasItem(DEFAULT_COLOR_CODE.toString())))
            .andExpect(jsonPath("$.[*].layoutType").value(hasItem(DEFAULT_LAYOUT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].row").value(hasItem(DEFAULT_ROW)))
            .andExpect(jsonPath("$.[*].column").value(hasItem(DEFAULT_COLUMN)))
            .andExpect(jsonPath("$.[*].layer").value(hasItem(DEFAULT_LAYER)))
            .andExpect(jsonPath("$.[*].lengthPosition").value(hasItem(DEFAULT_LENGTH_POSITION.intValue())))
            .andExpect(jsonPath("$.[*].widthPosition").value(hasItem(DEFAULT_WIDTH_POSITION.intValue())))
            .andExpect(jsonPath("$.[*].heightPosition").value(hasItem(DEFAULT_HEIGHT_POSITION.intValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH.intValue())))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH.intValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.intValue())));
    }
    
    @Test
    @Transactional
    public void getLoadingInfo() throws Exception {
        // Initialize the database
        loadingInfoRepository.saveAndFlush(loadingInfo);

        // Get the loadingInfo
        restLoadingInfoMockMvc.perform(get("/api/loading-infos/{id}", loadingInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loadingInfo.getId().intValue()))
            .andExpect(jsonPath("$.step").value(DEFAULT_STEP))
            .andExpect(jsonPath("$.materialCode").value(DEFAULT_MATERIAL_CODE.toString()))
            .andExpect(jsonPath("$.colorCode").value(DEFAULT_COLOR_CODE.toString()))
            .andExpect(jsonPath("$.layoutType").value(DEFAULT_LAYOUT_TYPE.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.row").value(DEFAULT_ROW))
            .andExpect(jsonPath("$.column").value(DEFAULT_COLUMN))
            .andExpect(jsonPath("$.layer").value(DEFAULT_LAYER))
            .andExpect(jsonPath("$.lengthPosition").value(DEFAULT_LENGTH_POSITION.intValue()))
            .andExpect(jsonPath("$.widthPosition").value(DEFAULT_WIDTH_POSITION.intValue()))
            .andExpect(jsonPath("$.heightPosition").value(DEFAULT_HEIGHT_POSITION.intValue()))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH.intValue()))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH.intValue()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLoadingInfo() throws Exception {
        // Get the loadingInfo
        restLoadingInfoMockMvc.perform(get("/api/loading-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoadingInfo() throws Exception {
        // Initialize the database
        loadingInfoRepository.saveAndFlush(loadingInfo);

        int databaseSizeBeforeUpdate = loadingInfoRepository.findAll().size();

        // Update the loadingInfo
        LoadingInfo updatedLoadingInfo = loadingInfoRepository.findById(loadingInfo.getId()).get();
        // Disconnect from session so that the updates on updatedLoadingInfo are not directly saved in db
        em.detach(updatedLoadingInfo);
        updatedLoadingInfo
            .step(UPDATED_STEP)
            .materialCode(UPDATED_MATERIAL_CODE)
            .colorCode(UPDATED_COLOR_CODE)
            .layoutType(UPDATED_LAYOUT_TYPE)
            .quantity(UPDATED_QUANTITY)
            .row(UPDATED_ROW)
            .column(UPDATED_COLUMN)
            .layer(UPDATED_LAYER)
            .lengthPosition(UPDATED_LENGTH_POSITION)
            .widthPosition(UPDATED_WIDTH_POSITION)
            .heightPosition(UPDATED_HEIGHT_POSITION)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT);

        restLoadingInfoMockMvc.perform(put("/api/loading-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLoadingInfo)))
            .andExpect(status().isOk());

        // Validate the LoadingInfo in the database
        List<LoadingInfo> loadingInfoList = loadingInfoRepository.findAll();
        assertThat(loadingInfoList).hasSize(databaseSizeBeforeUpdate);
        LoadingInfo testLoadingInfo = loadingInfoList.get(loadingInfoList.size() - 1);
        assertThat(testLoadingInfo.getStep()).isEqualTo(UPDATED_STEP);
        assertThat(testLoadingInfo.getMaterialCode()).isEqualTo(UPDATED_MATERIAL_CODE);
        assertThat(testLoadingInfo.getColorCode()).isEqualTo(UPDATED_COLOR_CODE);
        assertThat(testLoadingInfo.getLayoutType()).isEqualTo(UPDATED_LAYOUT_TYPE);
        assertThat(testLoadingInfo.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testLoadingInfo.getRow()).isEqualTo(UPDATED_ROW);
        assertThat(testLoadingInfo.getColumn()).isEqualTo(UPDATED_COLUMN);
        assertThat(testLoadingInfo.getLayer()).isEqualTo(UPDATED_LAYER);
        assertThat(testLoadingInfo.getLengthPosition()).isEqualTo(UPDATED_LENGTH_POSITION);
        assertThat(testLoadingInfo.getWidthPosition()).isEqualTo(UPDATED_WIDTH_POSITION);
        assertThat(testLoadingInfo.getHeightPosition()).isEqualTo(UPDATED_HEIGHT_POSITION);
        assertThat(testLoadingInfo.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testLoadingInfo.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testLoadingInfo.getHeight()).isEqualTo(UPDATED_HEIGHT);
    }

    @Test
    @Transactional
    public void updateNonExistingLoadingInfo() throws Exception {
        int databaseSizeBeforeUpdate = loadingInfoRepository.findAll().size();

        // Create the LoadingInfo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoadingInfoMockMvc.perform(put("/api/loading-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loadingInfo)))
            .andExpect(status().isBadRequest());

        // Validate the LoadingInfo in the database
        List<LoadingInfo> loadingInfoList = loadingInfoRepository.findAll();
        assertThat(loadingInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLoadingInfo() throws Exception {
        // Initialize the database
        loadingInfoRepository.saveAndFlush(loadingInfo);

        int databaseSizeBeforeDelete = loadingInfoRepository.findAll().size();

        // Get the loadingInfo
        restLoadingInfoMockMvc.perform(delete("/api/loading-infos/{id}", loadingInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LoadingInfo> loadingInfoList = loadingInfoRepository.findAll();
        assertThat(loadingInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoadingInfo.class);
        LoadingInfo loadingInfo1 = new LoadingInfo();
        loadingInfo1.setId(1L);
        LoadingInfo loadingInfo2 = new LoadingInfo();
        loadingInfo2.setId(loadingInfo1.getId());
        assertThat(loadingInfo1).isEqualTo(loadingInfo2);
        loadingInfo2.setId(2L);
        assertThat(loadingInfo1).isNotEqualTo(loadingInfo2);
        loadingInfo1.setId(null);
        assertThat(loadingInfo1).isNotEqualTo(loadingInfo2);
    }
}
