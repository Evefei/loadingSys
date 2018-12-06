package com.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.domain.GenerateLoadingParam;
import com.domain.Loading;
import com.repository.LoadingRepository;
import com.web.rest.errors.BadRequestAlertException;
import com.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.python.core.PyClass;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PyType;
import org.python.util.PythonInterpreter;
 
import java.util.Properties;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Loading.
 */
@RestController
@RequestMapping("/api")
public class LoadingResource {

    private final Logger log = LoggerFactory.getLogger(LoadingResource.class);

    private static final String ENTITY_NAME = "loading";

    private final LoadingRepository loadingRepository;

    public LoadingResource(LoadingRepository loadingRepository) {
        this.loadingRepository = loadingRepository;
    }

    /**
     * POST  /loadings : Create a new loading.
     *
     * @param loading the loading to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loading, or with status 400 (Bad Request) if the loading has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/loadings")
    @Timed
    public ResponseEntity<Loading> createLoading(@RequestBody Loading loading) throws URISyntaxException {
        log.debug("REST request to save Loading : {}", loading);
        if (loading.getId() != null) {
            throw new BadRequestAlertException("A new loading cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Loading result = loadingRepository.save(loading);
        return ResponseEntity.created(new URI("/api/loadings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /loadings : Updates an existing loading.
     *
     * @param loading the loading to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loading,
     * or with status 400 (Bad Request) if the loading is not valid,
     * or with status 500 (Internal Server Error) if the loading couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/loadings")
    @Timed
    public ResponseEntity<Loading> updateLoading(@RequestBody Loading loading) throws URISyntaxException {
        log.debug("REST request to update Loading : {}", loading);
        if (loading.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Loading result = loadingRepository.save(loading);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loading.getId().toString()))
            .body(result);
    }

    /**
     * GET  /loadings : get all the loadings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of loadings in body
     */
    @GetMapping("/loadings")
    @Timed
    public List<Loading> getAllLoadings() {
        log.debug("REST request to get all Loadings");
        return loadingRepository.findAll();
    }

    /**
     * GET  /loadings/:id : get the "id" loading.
     *
     * @param id the id of the loading to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loading, or with status 404 (Not Found)
     */
    @GetMapping("/loadings/{id}")
    @Timed
    public ResponseEntity<Loading> getLoading(@PathVariable Long id) {
        log.debug("REST request to get Loading : {}", id);
        Optional<Loading> loading = loadingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(loading);
    }

    /**
     * DELETE  /loadings/:id : delete the "id" loading.
     *
     * @param id the id of the loading to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/loadings/{id}")
    @Timed
    public ResponseEntity<Void> deleteLoading(@PathVariable Long id) {
        log.debug("REST request to delete Loading : {}", id);

        loadingRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * POST  /loadings : generate loading info.
     *
     * @param  loading the loading to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loading, or with status 400 (Bad Request) if the loading has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/generateLoading")
    @Timed
    public String generateLoading(@RequestBody GenerateLoadingParam generateLoadingParam) throws URISyntaxException {
        log.debug("REST request to save Loading : {}", generateLoadingParam);
        PythonInterpreter interpreter = new PythonInterpreter();
        //加载python类文件
        interpreter.execfile("./hello.py");
     // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
		//调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
//        int a = 5, b = 10;
        PyObject pyobj = pyFunction.__call__(generateLoadingParam); 
        //直接运行python语句
//        interpreter.exec("a=[5,2,3,9,4,0]; ");
//        interpreter.exec("print(sorted(a));");
        System.out.println(generateLoadingParam);
//        if (packingDetail.getId() != null) {
//            throw new BadRequestAlertException("A new loading cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        Loading loading.setLoadingNo();
        JSONArray returnLoading= new JSONArray();
        returnLoading.put("dsfsd");
        returnLoading.put("aaaaa");
        String jsonStr = returnLoading.toString();
        System.out.println(jsonStr);
//        Loading result = loadingRepository.save(loading);
        return jsonStr;
//		return ResponseEntity.created(new URI("/api/generateLoading/" + loading.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, loading.getId().toString()))
//            .body(loading);
    }
}
