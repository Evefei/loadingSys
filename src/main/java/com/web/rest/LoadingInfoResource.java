package com.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.domain.LoadingInfo;
import com.repository.LoadingInfoRepository;
import com.web.rest.errors.BadRequestAlertException;
import com.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing LoadingInfo.
 */
@RestController
@RequestMapping("/api")
public class LoadingInfoResource {

    private final Logger log = LoggerFactory.getLogger(LoadingInfoResource.class);

    private static final String ENTITY_NAME = "loadingInfo";

    private final LoadingInfoRepository loadingInfoRepository;

    public LoadingInfoResource(LoadingInfoRepository loadingInfoRepository) {
        this.loadingInfoRepository = loadingInfoRepository;
    }

    /**
     * POST  /loading-infos : Create a new loadingInfo.
     *
     * @param loadingInfo the loadingInfo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loadingInfo, or with status 400 (Bad Request) if the loadingInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/loading-infos")
    @Timed
    public ResponseEntity<LoadingInfo> createLoadingInfo(@RequestBody LoadingInfo loadingInfo) throws URISyntaxException {
        log.debug("REST request to save LoadingInfo : {}", loadingInfo);
        if (loadingInfo.getId() != null) {
            throw new BadRequestAlertException("A new loadingInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LoadingInfo result = loadingInfoRepository.save(loadingInfo);
        return ResponseEntity.created(new URI("/api/loading-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /loading-infos : Updates an existing loadingInfo.
     *
     * @param loadingInfo the loadingInfo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loadingInfo,
     * or with status 400 (Bad Request) if the loadingInfo is not valid,
     * or with status 500 (Internal Server Error) if the loadingInfo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/loading-infos")
    @Timed
    public ResponseEntity<LoadingInfo> updateLoadingInfo(@RequestBody LoadingInfo loadingInfo) throws URISyntaxException {
        log.debug("REST request to update LoadingInfo : {}", loadingInfo);
        if (loadingInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LoadingInfo result = loadingInfoRepository.save(loadingInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loadingInfo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /loading-infos : get all the loadingInfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of loadingInfos in body
     */
    @GetMapping("/loading-infos")
    @Timed
    public List<LoadingInfo> getAllLoadingInfos() {
        log.debug("REST request to get all LoadingInfos");
        return loadingInfoRepository.findAll();
    }

    /**
     * GET  /loading-infos/:id : get the "id" loadingInfo.
     *
     * @param id the id of the loadingInfo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loadingInfo, or with status 404 (Not Found)
     */
    @GetMapping("/loading-infos/{id}")
    @Timed
    public ResponseEntity<LoadingInfo> getLoadingInfo(@PathVariable Long id) {
        log.debug("REST request to get LoadingInfo : {}", id);
        Optional<LoadingInfo> loadingInfo = loadingInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(loadingInfo);
    }

    /**
     * DELETE  /loading-infos/:id : delete the "id" loadingInfo.
     *
     * @param id the id of the loadingInfo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/loading-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteLoadingInfo(@PathVariable Long id) {
        log.debug("REST request to delete LoadingInfo : {}", id);

        loadingInfoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
